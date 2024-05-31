package kangnamuniv.elibrary.repository;

import kangnamuniv.elibrary.dto.BookSearchResultDTO;
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

        database.add(new Book(3,"제목4","저자3","출판사3","PDF경로3",3));
        database.add(new Book(3,"제목5","저자3","출판사3","PDF경로3",3));
        database.add(new Book(3,"제목6","저자3","출판사3","PDF경로3",3));
        database.add(new Book(3,"제목7","저자3","출판사3","PDF경로3",3));
        database.add(new Book(3,"제목8","저자3","출판사3","PDF경로3",3));
        database.add(new Book(3,"제목9","저자3","출판사3","PDF경로3",3));
        database.add(new Book(3,"제목10","저자3","출판사3","PDF경로3",3));
        database.add(new Book(3,"제목11","저자3","출판사3","PDF경로3",3));
        database.add(new Book(3,"제목12","저자3","출판사3","PDF경로3",3));
        database.add(new Book(3,"제목13","저자3","출판사3","PDF경로3",3));
        database.add(new Book(3,"제목14","저자3","출판사3","PDF경로3",3));
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
    public BookSearchResultDTO searchBooksByTitle(String searchWord, int page, int size) {

        int skip = (page - 1) * size;
        int limit = size;

        Long bookCount = database.stream()
                .filter(book -> book.getTitle().contains(searchWord))
                .count();

        int totalPage = (int) (bookCount / size);

        if (bookCount % size != 0) {
            totalPage++;
        }

        List<Book> foundBooks = database.stream()
                .filter(book -> book.getTitle().contains(searchWord))
                .skip(skip)
                .limit(limit)
                .collect(Collectors.toList());

        return new BookSearchResultDTO(foundBooks, totalPage);
    }
}
