package kangnamuniv.elibrary;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private BookService service;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("Books", service.getBook());
        return "home";
    }
}

