package kangnamuniv.elibrary.service;
import kangnamuniv.elibrary.entity.Book;
import kangnamuniv.elibrary.entity.Loan;
import kangnamuniv.elibrary.repository.BookMemoryDAOImple;
import kangnamuniv.elibrary.repository.LoanRepository;
import kangnamuniv.elibrary.repository.MemoryLoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private MemoryLoanRepository loanRepository;

    @Autowired
    private BookMemoryDAOImple bookMemoryDAOImple;

    @Override
    public Loan loanBook(Long userId, int bookId) {
        Book book = bookMemoryDAOImple.findById(bookId);
        if(book.getAvailableCount() > 0) {
            ArrayList<Loan> existingLoans = loanRepository.findByUserIdBookIdAndIsReturnedFalse(userId, bookId);
            if (existingLoans.isEmpty()) {
                LocalDateTime loanDate = LocalDateTime.now();
                LocalDateTime dueDate = loanDate.plusWeeks(2);
                Loan loan = new Loan(null, bookId, userId, loanDate, dueDate, false);
                Loan savedLoan = loanRepository.save(loan);

                bookMemoryDAOImple.saveAvailableCount(bookId, book.getAvailableCount() - 1);
                return savedLoan;
            }
        }
        return null;
    }

    @Override
    public Loan returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setReturned(true);

        Book book = bookMemoryDAOImple.findById(loan.getBookId());
        bookMemoryDAOImple.saveAvailableCount(book.getId(), book.getAvailableCount() + 1);
        return loanRepository.save(loan);
    }

    @Override
    public ArrayList<Loan> findLoansByUser(Long userId) {
        return loanRepository.findByUserIdAndIsReturnedFalse(userId);
    }
}
