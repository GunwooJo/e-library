package kangnamuniv.elibrary.repository;


import kangnamuniv.elibrary.entity.BookRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRequestDAOImple implements BookRequestDAO {
    private List<BookRequest> RequestDatabase;
    private int RequestId = 1;

    public BookRequestDAOImple() {
        RequestDatabase = new ArrayList<>();
        RequestDatabase.add(new BookRequest(RequestId++, "호밀밭의 파수꾼", "J.D.샐린저", "민음사"));
        RequestDatabase.add(new BookRequest(RequestId++, "달과 6펜스", "서머 셋몸", "홍신문화사"));
    }
    @Override
    public List<BookRequest> getBookRequest() {
        return RequestDatabase;
    }

    @Override
    public void saveBookRequest(BookRequest bookRequest) {
        bookRequest.setId(RequestId++);
        RequestDatabase.add(bookRequest);
    }

    @Override
    public void deleteBookRequest(int id) {
        {
            RequestDatabase.removeIf(bookRequest -> bookRequest.getId() == id);
        }
    }
}
