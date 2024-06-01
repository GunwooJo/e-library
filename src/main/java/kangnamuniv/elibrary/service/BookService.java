package kangnamuniv.elibrary.service;

import kangnamuniv.elibrary.dto.BookSearchResultDTO;
import kangnamuniv.elibrary.entity.Book;

import java.util.List;

public interface BookService {
    public List<Book> getBook();
    void saveBook(Book book);
    Book findById(int id);
    BookSearchResultDTO searchBooksByTitle(String searchWord, int page, int size);
    BookSearchResultDTO searchBooksByAuthor(String searchWord, int page, int size);
}
