package kangnamuniv.elibrary;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookServiceImpl implements BookService{

    @Autowired
    private BookDAO dao;

    @Override
    public List<Book> getBook() {
        return dao.getBook();
    }
}
