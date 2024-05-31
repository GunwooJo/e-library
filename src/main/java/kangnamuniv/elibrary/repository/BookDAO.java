package kangnamuniv.elibrary.repository;

import kangnamuniv.elibrary.dto.BookSearchResultDTO;
import kangnamuniv.elibrary.entity.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getBook();
    void saveBook(Book book);
    Book findById(int id);
    BookSearchResultDTO searchBooksByTitle(String searchWord, int page, int size);
}
