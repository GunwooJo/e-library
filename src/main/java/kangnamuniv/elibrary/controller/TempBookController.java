package kangnamuniv.elibrary.controller;

import kangnamuniv.elibrary.dto.BookSearchResultDTO;
import kangnamuniv.elibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TempBookController {

    private final BookService bookService;

    @GetMapping("/book/search")
    public String searchByTitle(@RequestParam String keyword, @RequestParam String target, @RequestParam int page,  Model model) {

        BookSearchResultDTO result;

        if ("title".equals(target)) {
            result = bookService.searchBooksByTitle(keyword, page, 10);
        } else if ("author".equals(target)) {
            result = bookService.searchBooksByAuthor(keyword, page, 10);
        } else {
            return "redirect:/errorPage.html";
        }

        model.addAttribute("Books", result.getBooks());
        model.addAttribute("totalPage", result.getTotalPage());
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("target", target);

        return "searchBookList";
    }
}
