package kangnamuniv.elibrary.dto;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class BookRequestDTO {
    String title;
    String author;
    String publisher;
}