package kangnamuniv.elibrary.repository;

import kangnamuniv.elibrary.service.BookDAO;
import kangnamuniv.elibrary.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookMemoryDAOImple implements BookDAO {
    private List<Book> database;

    public BookMemoryDAOImple() {
        database = new ArrayList<>();
        database.add(new Book(1,"이방인","알베르 카뮈","민음사","PDF경로1",3));
        database.add(new Book(2,"어린 왕자","생텍쥐페리","새움","PDF경로2",3));
        database.add(new Book(3,"인간 실격","다자이 오사무","잇북","PDF경로3",3));
    }

    @Override
    public List<Book> getBook() {
        return database;
    }

    @Override
    public void saveBook(Book book) {
        database.add(book);
    }

    @Override
    public Book findById(int id){
        for (Book book : database) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    @Override
    public void deleteBook(int id){
        database.removeIf(book -> book.getId() == id);
    }
}
