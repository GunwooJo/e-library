package kangnamuniv.elibrary.dto;

import kangnamuniv.elibrary.entity.Book;
import kangnamuniv.elibrary.entity.Loan;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LoanBookDTO {
    private Long id;
    private int bookId;
    private Long userId;
    private LocalDateTime loanDate;
    private LocalDateTime dueDate;
    private boolean isReturned;

    private String title;
    private String author;
    private String publisher;
    private String pdfPath;
    private int AvailableCount;

    public LoanBookDTO(Loan loan, Book book) {
        this.id = loan.getId();
        this.bookId =  loan.getBookId();
        this.userId = loan.getUserId();
        this.loanDate = loan.getLoanDate();
        this.dueDate = loan.getDueDate();
        this.isReturned = loan.isReturned();

        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.pdfPath = book.getPdfPath();
        this.AvailableCount = book.getAvailableCount();
    }
}
