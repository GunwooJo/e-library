package kangnamuniv.elibrary.dto;

import kangnamuniv.elibrary.entity.Book;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class BookDTO {
    String title;
    String author;
    String publisher;
    String pdfPath;
    int availableCount;

    public Book toEntity() {
        Book book = Book.builder()
                .title(this.title)
                .author(this.author)
                .publisher(this.publisher)
                .pdfPath(this.pdfPath)
                .availableCount(this.availableCount)
                .build();
        return book;
    }
}
