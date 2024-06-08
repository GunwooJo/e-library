package kangnamuniv.elibrary.repository;

import kangnamuniv.elibrary.entity.BookRequest;

import java.util.List;

public interface BookRequestDAO {
    List<BookRequest> getBookRequest();
    void saveBookRequest(BookRequest bookRequest);
    void deleteBookRequest(int id);
}
