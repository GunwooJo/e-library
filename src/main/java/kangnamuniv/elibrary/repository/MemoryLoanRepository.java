package kangnamuniv.elibrary.repository;

import kangnamuniv.elibrary.entity.Loan;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
@Repository
public class MemoryLoanRepository implements LoanRepository {
    private ArrayList<Loan> loans = new ArrayList<>();
    private Long currentId = 1L;
    @Override
    public Loan save(Loan loan) {
        if (loan.getId() == null) {
            loan.setId(currentId++);
            loans.add(loan);
        } else {
            loans = (ArrayList<Loan>) loans.stream()
                    .map(l -> l.getId().equals(loan.getId()) ? loan : l)
                    .collect(Collectors.toList());
        }
        return loan;
    }

    @Override
    public Optional<Loan> findById(Long id) {
        return loans.stream().filter(loan -> loan.getId().equals(id)).findFirst();
    }

    @Override
    public ArrayList<Loan> findByUserIdAndIsReturnedFalse(Long userId) {
        return (ArrayList<Loan>) loans.stream()
                .filter(loan -> loan.getUserId().equals(userId) && !loan.isReturned())
                .collect(Collectors.toList());
    }

    @Override
    public ArrayList<Loan> findByBookIdAndIsReturnedFalse(Long bookId) {
        return (ArrayList<Loan>) loans.stream()
                .filter(loan -> loan.getBookId().equals(bookId) && !loan.isReturned())
                .collect(Collectors.toList());
    }
}
