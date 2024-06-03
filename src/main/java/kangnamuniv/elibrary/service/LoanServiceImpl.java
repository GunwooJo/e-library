package kangnamuniv.elibrary.service;
import kangnamuniv.elibrary.entity.Loan;
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

    @Override
    public Loan loanBook(Long userId, int bookId) {
        ArrayList<Loan> existingLoans = loanRepository.findByBookIdAndIsReturnedFalse(bookId);
        if (existingLoans.isEmpty()) {
            LocalDateTime loanDate = LocalDateTime.now();
            LocalDateTime dueDate = loanDate.plusWeeks(2);
            Loan loan = new Loan(null, bookId, userId, loanDate, dueDate, false);
            Loan savedLoan = loanRepository.save(loan);
            return savedLoan;
        }
        return null;
    }

    @Override
    public Loan returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setReturned(true);
        return loanRepository.save(loan);
    }

    @Override
    public ArrayList<Loan> findLoansByUser(Long userId) {
        return loanRepository.findByUserIdAndIsReturnedFalse(userId);
    }
}
