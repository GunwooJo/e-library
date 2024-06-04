package kangnamuniv.elibrary.entity;


import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString
public class Book {
    int id;
    String title;
    String author;
    String publisher;
    String pdfPath;
    int availableCount;
}
