package kangnamuniv.elibrary.service;

import kangnamuniv.elibrary.entity.Loan;

import java.util.ArrayList;

public interface LoanService {
    Loan loanBook(Long userId, Long bookId);
    Loan returnBook(Long loanId);
    ArrayList<Loan> findLoansByUser(Long userId);
}
