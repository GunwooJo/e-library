package kangnamuniv.elibrary.service;

import kangnamuniv.elibrary.dto.BookRequestDTO;
import kangnamuniv.elibrary.entity.BookRequest;
import kangnamuniv.elibrary.repository.BookRequestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookRequestImpl implements BookRequestService{

    @Autowired
    private BookRequestDAO dao;

    @Override
    public List<BookRequest> getBookRequest() {
        return dao.getBookRequest();
    }
    @Override
    public BookRequestDTO saveBookRequest(BookRequestDTO bookRequestDTO) {
        BookRequest bookRequest = BookRequest.builder()
                .title(bookRequestDTO.getTitle())
                .author(bookRequestDTO.getAuthor())
                .publisher(bookRequestDTO.getPublisher())
                .build();
        dao.saveBookRequest(bookRequest);
        return bookRequestDTO;
    }
    @Override
    public void deleteBookRequest(int id) {
        {
            dao.deleteBookRequest(id);
        }
    }
}
