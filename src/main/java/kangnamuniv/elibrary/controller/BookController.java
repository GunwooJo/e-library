package kangnamuniv.elibrary.controller;


import kangnamuniv.elibrary.service.BookService;
import kangnamuniv.elibrary.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
public class BookController {

    @Autowired
    private BookService service;

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("Books", service.getBook());
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
}

