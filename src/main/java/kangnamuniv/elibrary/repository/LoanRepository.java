package kangnamuniv.elibrary.repository;

import kangnamuniv.elibrary.entity.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface LoanRepository {
    Loan save(Loan loan);
    Optional<Loan> findById(Long id);
    ArrayList<Loan> findByUserIdAndIsReturnedFalse(Long userId);
    ArrayList<Loan> findByBookIdAndIsReturnedFalse(int bookId);
    ArrayList<Loan> findByUserIdBookIdAndIsReturnedFalse(Long userId, int bookId);
    List<Loan> findByDueUnderNumDays(long num);
}
