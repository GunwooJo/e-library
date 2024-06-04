package kangnamuniv.elibrary.controller;


import jakarta.servlet.http.HttpSession;
import kangnamuniv.elibrary.dto.BookSearchResultDTO;
import kangnamuniv.elibrary.entity.Loan;
import kangnamuniv.elibrary.entity.User;
import kangnamuniv.elibrary.service.BookService;
import kangnamuniv.elibrary.entity.Book;
import kangnamuniv.elibrary.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    @Autowired
    private BookService service;

    @Autowired
    private LoanService loanService;

    @RequestMapping("/home")
    public String home(Model model, HttpSession session) {
        model.addAttribute("Books", service.getBook());
        User user = (User) session.getAttribute("loggedInUser");
        model.addAttribute("user", user);
        return "home";
    }
    private BookService getService() {
        return service;
    }

    @GetMapping("/insertForm")
    public String save(Model model) {
        return "insertForm";
    }

    @PostMapping("/insert")
    public String saveBook(Book book) {
        service.saveBook(book);
        return "redirect:/home";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") int id, Model model) {
        Book book = service.findById(id);
        model.addAttribute("book", book);
        return "detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        service.deleteBook(id);
        return "redirect:/home";
    }

    @GetMapping("/book/search")
    public String searchByTitle(@RequestParam String keyword, @RequestParam String target, @RequestParam int page,  Model model) {

        BookSearchResultDTO result;

        if ("title".equals(target)) {
            result = service.searchBooksByTitle(keyword, page, 10);
        } else if ("author".equals(target)) {
            result = service.searchBooksByAuthor(keyword, page, 10);
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

    @GetMapping("/book/loan/{id}")
    public String loan(@PathVariable("id") int bookId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        Loan loan = loanService.loanBook(user.getId(), bookId);
        return "redirect:/user/mypage";
    }
}

