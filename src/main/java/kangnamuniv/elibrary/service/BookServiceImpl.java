package kangnamuniv.elibrary.service;

import kangnamuniv.elibrary.dto.BookDTO;
import kangnamuniv.elibrary.dto.BookSearchResultDTO;
import kangnamuniv.elibrary.entity.Book;
import kangnamuniv.elibrary.repository.BookDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDAO dao;

    private final String uploadPath = "src/main/resources/uploads/pdf";

    @Override
    public List<Book> getBook() {
        return dao.getBook();
    }

    @Override
    public BookDTO saveBook(BookDTO bookDTO) throws Exception {

        Path path = Paths.get(uploadPath);
        if (!Files.exists(path)) {
            Path directories = Files.createDirectories(path);
            log.info("pdf 폴더 생성: {}", directories);
        }

        String filename = UUID.randomUUID() + "_" + bookDTO.getPdf().getOriginalFilename();
        Path filePath = Paths.get(uploadPath, filename);
        byte[] bytes = bookDTO.getPdf().getBytes();
        Files.write(filePath, bytes);

        dao.saveBook(bookDTO.toEntity(filename));
        return bookDTO;
    }

    @Override
    public Book findById(int id) {
        return dao.findById(id);
    }
    @Override
    public void deleteBook(int id){
        dao.deleteBook(id);
    }
    @Override
    public BookSearchResultDTO searchBooksByTitle(String searchWord, int page, int size) {
        return dao.searchBooksByTitle(searchWord, page, size);
    }

    @Override
    public BookSearchResultDTO searchBooksByAuthor(String searchWord, int page, int size) {
        return dao.searchBooksByAuthor(searchWord, page, size);
    }

    @Override
    public void updateBook(Book book){
        dao.updateBook(book);
    }

}
