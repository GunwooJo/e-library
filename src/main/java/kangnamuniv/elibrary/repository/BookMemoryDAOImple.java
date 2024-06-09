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
        database.add(new Book(id++,"이방인","알베르 키위","민음사","The Stranger.pdf",3));
        database.add(new Book(id++,"네 안의 진정한 남자를 깨워라(The Way Of The Superior Man)","mirror","PDFy mirror","The Way Of The Superior Man.pdf",2));
        database.add(new Book(id++,"올림포스의 신(The lightning thief)","Riordan, Rick","Miramax","01_The_Lightning_Thief.pdf",1));
        database.add(new Book(id++,"지킬 섬의 생물(The Creature From Jekyll Island)","mirror","PDFy mirror","The Creature From Jekyll Island.pdf",0));
        database.add(new Book(id++,"인생에서 그림을 그리는 완벽한 가이드(Complete Guide to Drawing from Life)","Bridgman","PDFy mirror","Bridgman - Complete Guide to Drawing from Life_text.pdf",3));
        database.add(new Book(id++,"제목01","저자","출판사","data.pdf",3));
        database.add(new Book(id++,"제목02","저자","출판사","data.pdf",3));
        database.add(new Book(id++,"제목03","저자","출판사","data.pdf",3));
        database.add(new Book(id++,"제목04","저자","출판사","data.pdf",3));
        database.add(new Book(id++,"제목05","저자","출판사","data.pdf",3));
        database.add(new Book(id++,"제목06","저자","출판사","data.pdf",3));
        database.add(new Book(id++,"제목07","저자","출판사","data.pdf",3));
        database.add(new Book(id++,"제목08","저자","출판사","data.pdf",3));
        database.add(new Book(id++,"제목09","저자","출판사","data.pdf",3));
        database.add(new Book(id++,"제목10","저자","출판사","data.pdf",3));
        database.add(new Book(id++,"제목11","저자","출판사","data.pdf",3));
        database.add(new Book(id++,"제목12","저자","출판사","data.pdf",3));
        database.add(new Book(id++,"제목13","저자","출판사","data.pdf",3));
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
    public void updateBook(Book book){
        for(int i=0; i < database.size(); i++) {
            if(database.get(i).getId() == book.getId()) {
                database.set(i, book);
                return;
            }
        }
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
