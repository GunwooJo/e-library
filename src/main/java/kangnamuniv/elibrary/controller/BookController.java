package kangnamuniv.elibrary.controller;

import jakarta.servlet.http.HttpSession;
import kangnamuniv.elibrary.entity.Loan;
import kangnamuniv.elibrary.service.LoanService;
import jakarta.servlet.http.HttpServletRequest;
import kangnamuniv.elibrary.dto.BookDTO;
import kangnamuniv.elibrary.dto.BookSearchResultDTO;
import kangnamuniv.elibrary.entity.User;
import kangnamuniv.elibrary.service.BookService;
import kangnamuniv.elibrary.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class BookController {

    @Autowired
    private BookService service;

    @Autowired
    private LoanService loanService;

    @RequestMapping("/home")
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("Books", service.getBook());

        User loggedInUser = (User) request.getSession(false).getAttribute("loggedInUser");

        if(loggedInUser != null) {
            model.addAttribute("isLoggedIn", true);
            model.addAttribute("userRole", loggedInUser.getRole());
            model.addAttribute("user", loggedInUser);
        } else {
            model.addAttribute("isLoggedIn", false);
        }
        return "home";
    }

    @GetMapping("/insertForm")
    public String save(Model model) {
        return "insertForm";
    }

    @PostMapping("/insert")
    public String saveBook(@ModelAttribute BookDTO bookDTO, Model model) {
        try {
            BookDTO savedBook = service.saveBook(bookDTO);
            model.addAttribute("savedBook", savedBook);

        } catch (Exception e) {
            log.error("pdf 저장 에러: ", e);
            model.addAttribute("error", e.getMessage());
        }
        return "addedBookInfo";
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

    @PostMapping("/update")
    public String update(@ModelAttribute Book book) {
        service.updateBook(book);
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

//    ======  Loan 영역  ======
    @GetMapping("/book/loan/{id}")  // 대출
    public String loan(@PathVariable("id") int bookId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        Loan loan = loanService.loanBook(user.getId(), bookId);
        String msg = loan == null ? "대출 불가합니다." : "1권 대출됐습니다. 마이페이지로 이동합니다.";
        model.addAttribute("msg", msg);
        System.out.println("Loaned Book: " + loan);
        return "book/bookLoan";
    }
    @GetMapping("/book/loan/return/{id}") // 반납
    public String loanReturn(@PathVariable("id") Long loanId, Model model) {
        Loan loan = loanService.returnBook(loanId);
        System.out.println("Returned Loan: " + loan);
        return "redirect:/user/mypage";
    }
    @GetMapping("/book/loan/detail/{id}")
    public String loanDetail(@PathVariable("id") int bookId, Model model) {
        Book book = service.findById(bookId);
        model.addAttribute("book", book);
        return "book/bookDetail";
    }
}

