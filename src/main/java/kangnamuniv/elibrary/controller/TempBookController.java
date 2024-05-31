package kangnamuniv.elibrary.controller;

import kangnamuniv.elibrary.dto.BookSearchResultDTO;
import kangnamuniv.elibrary.entity.Book;
import kangnamuniv.elibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TempBookController {

    private final BookService bookService;

    @GetMapping("/book/search")
    public String search(@RequestParam String title, @RequestParam int page,  Model model) {

        BookSearchResultDTO result = bookService.searchBooksByTitle(title, page, 10);
        model.addAttribute("Books", result.getBooks());
        model.addAttribute("totalPage", result.getTotalPage());
        model.addAttribute("title", title);
        model.addAttribute("page", page);

        return "searchBookList";
    }
}
