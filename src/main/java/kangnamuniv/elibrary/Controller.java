package kangnamuniv.elibrary;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

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

    @RequestMapping("/insertForm")
    public String save(Model model) {
        return "insertForm";
    }

    @RequestMapping("/insert")
    public String saveBook(Book book) {
        service.saveBook(book);
        return "redirect:/home";
    }

    @RequestMapping("/detail")
    public String detail(@RequestParam("id") int id, Model model) {
        Book book = service.findById(id);
        model.addAttribute("book", book);
        return "detail";
    }
}

