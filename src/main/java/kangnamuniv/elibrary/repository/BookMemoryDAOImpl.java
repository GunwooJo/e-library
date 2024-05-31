package kangnamuniv.elibrary.repository;

import kangnamuniv.elibrary.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookMemoryDAOImpl implements BookDAO{
    private List<Book> database;

    public BookMemoryDAOImpl() {
        database = new ArrayList<>();
        database.add(new Book(1,"제목1","저자1","출판사1","PDF경로1",3));
        database.add(new Book(2,"제목2","저자2","출판사2","PDF경로2",3));
        database.add(new Book(3,"제목3","저자3","출판사3","PDF경로3",3));
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
    public List<Book> searchBooksByTitle(String searchWord, int page, int size) {

        int skip = (page - 1) * size;
        int limit = size;

        return database.stream()
                .filter(book -> book.getTitle().contains(searchWord))
                .skip(skip)
                .limit(limit)
                .collect(Collectors.toList());
    }
}
