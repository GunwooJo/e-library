package kangnamuniv.elibrary;

import java.util.List;

public interface BookDAO {
    List<Book> getBook();
    void saveBook(Book book);
    Book findById(int id);
}
