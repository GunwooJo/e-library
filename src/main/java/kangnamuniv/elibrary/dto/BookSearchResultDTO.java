package kangnamuniv.elibrary.dto;

import kangnamuniv.elibrary.entity.Book;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BookSearchResultDTO {

    private List<Book> books = new ArrayList<>();

    private int totalPage;

}
