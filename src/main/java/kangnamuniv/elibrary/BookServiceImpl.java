package kangnamuniv.elibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDAO dao;

    @Override
    public List<Book> getBook() {
        return dao.getBook();
    }

    @Override
    public void saveBook(Book book) {
        dao.saveBook(book);
    }

    @Override
    public Book findById(int id) {
        return dao.findById(id);
    }
}
