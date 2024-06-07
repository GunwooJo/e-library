package kangnamuniv.elibrary.service;

import kangnamuniv.elibrary.entity.Loan;

import java.util.ArrayList;
import java.util.List;

public interface LoanService {
    Loan loanBook(Long userId, int bookId);
    Loan returnBook(Long loanId);
    ArrayList<Loan> findLoansByUser(Long userId);
    List<Loan> findEmailAlertLoan();
}
