package kangnamuniv.elibrary.service;

import kangnamuniv.elibrary.entity.Book;

import java.util.List;

public interface BookDAO {
    public List<Book> getBook();
    void saveBook(Book book);
    Book findById(int id);
    void deleteBook(int id);
}
