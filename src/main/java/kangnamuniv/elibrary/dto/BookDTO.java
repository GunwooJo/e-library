package kangnamuniv.elibrary.dto;

import kangnamuniv.elibrary.entity.Book;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class BookDTO {
    String title;
    String author;
    String publisher;
    MultipartFile pdf;
    int availableCount;

    public Book toEntity(String pdfPath) {
        Book book = Book.builder()
                .title(this.title)
                .author(this.author)
                .publisher(this.publisher)
                .pdfPath(pdfPath)
                .availableCount(this.availableCount)
                .build();
        return book;
    }
}
