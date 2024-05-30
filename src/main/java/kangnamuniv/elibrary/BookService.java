package kangnamuniv.elibrary;

import java.util.List;

public interface BookService {
    public List<Book> getBook();
    void saveBook(Book book);
    Book findById(int id);
}
