package kangnamuniv.elibrary.repository;

import kangnamuniv.elibrary.entity.Loan;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    public ArrayList<Loan> findByBookIdAndIsReturnedFalse(int bookId) {
        return (ArrayList<Loan>) loans.stream()
                .filter(loan -> loan.getBookId() == (bookId) && !loan.isReturned())
                .collect(Collectors.toList());
    }

    @Override
    public ArrayList<Loan> findByUserIdBookIdAndIsReturnedFalse(Long userId, int bookId) {
        return (ArrayList<Loan>) loans.stream()
                .filter(loan -> loan.getUserId().equals(userId) && loan.getBookId() == (bookId) && !loan.isReturned())
                .collect(Collectors.toList());
    }

    @Override
    public List<Loan> findEmailAlertLoan() {
        return loans.stream()
                .filter(loan -> {
                    Duration duration = Duration.between(loan.getDueDate(), LocalDateTime.now());
                    long days = duration.toDays();
                    return days < 2;
                }).collect(Collectors.toList());
    }
}
