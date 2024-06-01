package kangnamuniv.elibrary.service;

import kangnamuniv.elibrary.dto.BookSearchResultDTO;
import kangnamuniv.elibrary.entity.Book;
import kangnamuniv.elibrary.repository.BookDAO;
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

    @Override
    public BookSearchResultDTO searchBooksByTitle(String searchWord, int page, int size) {
        return dao.searchBooksByTitle(searchWord, page, size);
    }

    @Override
    public BookSearchResultDTO searchBooksByAuthor(String searchWord, int page, int size) {
        return dao.searchBooksByAuthor(searchWord, page, size);
    }
}
