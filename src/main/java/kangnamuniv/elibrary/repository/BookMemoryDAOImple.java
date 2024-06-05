package kangnamuniv.elibrary.repository;

import kangnamuniv.elibrary.dto.BookSearchResultDTO;
import kangnamuniv.elibrary.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookMemoryDAOImple implements BookDAO {
    private List<Book> database;
    private int id = 1;

    public BookMemoryDAOImple() {
        database = new ArrayList<>();
        database.add(new Book(id++,"이방인","알베르 카뮈","민음사","PDF경로1",3));
        database.add(new Book(id++,"어린 왕자","생텍쥐페리","새움","PDF경로2",3));
        database.add(new Book(id++,"인간 실격","다자이 오사무","잇북","PDF경로3",3));
        database.add(new Book(id++,"제목4","저자3","출판사3","PDF경로3",3));
        database.add(new Book(id++,"제목5","저자3","출판사3","PDF경로3",3));
        database.add(new Book(id++,"제목6","저자3","출판사3","PDF경로3",3));
        database.add(new Book(id++,"제목7","저자3","출판사3","PDF경로3",3));
        database.add(new Book(id++,"제목8","저자3","출판사3","PDF경로3",3));
        database.add(new Book(id++,"제목9","저자3","출판사3","PDF경로3",3));
        database.add(new Book(id++,"제목10","저자3","출판사3","PDF경로3",3));
        database.add(new Book(id++,"제목11","저자3","출판사3","PDF경로3",3));
        database.add(new Book(id++,"제목12","저자3","출판사3","PDF경로3",3));
        database.add(new Book(id++,"제목13","저자3","출판사3","PDF경로3",3));
        database.add(new Book(id++,"제목14","저자3","출판사3","PDF경로3",3));
    }

    @Override
    public List<Book> getBook() {
        return database;
    }

    @Override
    public void saveBook(Book book) {
        book.setId(id++);
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

    @Override
    public void saveAvailableCount(int bookId, int availableCount) {
        database.stream()
                .filter(book -> book.getId() == bookId)
                .forEach(book -> book.setAvailableCount(availableCount));
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

    @Override
    public BookSearchResultDTO searchBooksByAuthor(String searchWord, int page, int size) {

        int skip = (page - 1) * size;
        int limit = size;

        Long bookCount = database.stream()
                .filter(book -> book.getAuthor().contains(searchWord))
                .count();

        int totalPage = (int) (bookCount / size);

        if (bookCount % size != 0) {
            totalPage++;
        }

        List<Book> foundBooks = database.stream()
                .filter(book -> book.getAuthor().contains(searchWord))
                .skip(skip)
                .limit(limit)
                .collect(Collectors.toList());

        return new BookSearchResultDTO(foundBooks, totalPage);
    }
}
