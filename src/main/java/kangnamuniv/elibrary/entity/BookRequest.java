package kangnamuniv.elibrary.entity;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class BookRequest {
    int id;
    String title;
    String author;
    String publisher;
}
