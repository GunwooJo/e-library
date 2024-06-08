package kangnamuniv.elibrary.service;

import kangnamuniv.elibrary.dto.BookRequestDTO;
import kangnamuniv.elibrary.entity.BookRequest;

import java.util.List;

public interface BookRequestService {
    public List<BookRequest> getBookRequest();
    BookRequestDTO saveBookRequest(BookRequestDTO bookRequestDTO);
    void deleteBookRequest(int id);
}
