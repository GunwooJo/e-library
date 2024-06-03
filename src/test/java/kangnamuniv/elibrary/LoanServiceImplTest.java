package kangnamuniv.elibrary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import kangnamuniv.elibrary.entity.Loan;
import kangnamuniv.elibrary.repository.MemoryLoanRepository;
import kangnamuniv.elibrary.service.LoanService;
import kangnamuniv.elibrary.service.LoanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class LoanServiceImplTest {

//    @Autowired
//    private LoanService loanService;
//
//
//    @Test
//    public void testLoanBook() {
//        Loan newLoan = loanService.loanBook(1L, 1L);
//        assertThat(newLoan).isNotNull();
//        assertThat(newLoan.getBookId()).isEqualTo(1L);
//        assertThat(newLoan.getUserId()).isEqualTo(1L);
//    }
    @InjectMocks
    private LoanServiceImpl loanService;

    @Mock
    private MemoryLoanRepository loanRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoanBook() {
        Long userId = 1L;
        int bookId = 1;
        System.out.println("Testing loanBook with userId: {"+userId+"} and bookId: {"+bookId+"}" );
        when(loanRepository.findByBookIdAndIsReturnedFalse(bookId)).thenReturn(new ArrayList<>());

        Loan loan = new Loan(null, bookId, userId, LocalDateTime.now(), LocalDateTime.now().plusWeeks(2), false);
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        Loan result = loanService.loanBook(userId, bookId);
        System.out.println("Testing loanBook with Loan: {"+result.toString()+"}" );
        assertNotNull(result);
        assertEquals(bookId, result.getBookId());
        assertEquals(userId, result.getUserId());
        assertFalse(result.isReturned());
    }

    @Test
    public void testReturnBook() {
        Long loanId = 1L;
        Loan loan = new Loan(loanId, 1, 1L, LocalDateTime.now(), LocalDateTime.now().plusWeeks(2), false);
        when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        Loan result = loanService.returnBook(loanId);

        assertNotNull(result);
        assertTrue(result.isReturned());
    }

    @Test
    public void testFindLoansByUser() {
        Long userId = 1L;
        ArrayList<Loan> loans = new ArrayList<>();
        loans.add(new Loan(1L, 1, userId, LocalDateTime.now(), LocalDateTime.now().plusWeeks(2), false));
        when(loanRepository.findByUserIdAndIsReturnedFalse(userId)).thenReturn(loans);

        ArrayList<Loan> result = loanService.findLoansByUser(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(userId, result.get(0).getUserId());
    }
}
