package kangnamuniv.elibrary.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kangnamuniv.elibrary.dto.LoanBookDTO;
import kangnamuniv.elibrary.dto.UserDTO;
import kangnamuniv.elibrary.entity.Book;
import kangnamuniv.elibrary.entity.Loan;
import kangnamuniv.elibrary.entity.User;
import kangnamuniv.elibrary.entity.UserRole;
import kangnamuniv.elibrary.service.BookService;
import kangnamuniv.elibrary.service.LoanService;
import kangnamuniv.elibrary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private LoanService loanService;

    @PostMapping("/user/signup")
    public String signUp(@ModelAttribute UserDTO userDTO) {

        userService.signUp(userDTO);
        return "redirect:/user/login";
    }

    @PostMapping("/user/login")
    public String login(@ModelAttribute UserDTO userDTO, HttpSession session) {

        Optional<User> loggedInUser = userService.login(userDTO);

        if (loggedInUser.isEmpty()) {
            return "redirect:/user/login?error=true";
        }
        session.setAttribute("loggedInUser", loggedInUser.get());
        session.setMaxInactiveInterval(1800);   //세션 유효시간 30분 = 1800초

        return "redirect:/home";
    }

    @GetMapping("/user/login")
    public String loginPage(@RequestParam(required = false) String error, Model model) {

        if ("true".equals(error)) {
            model.addAttribute("loginErrorMsg", "존재하지 않는 회원이거나 비밀번호가 일치하지 않습니다.");
        }

        return "/user/login";
    }
    public String extractUsername(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        int atIndex = email.indexOf("@");
        return email.substring(0, atIndex);
    }

    @GetMapping("/user/mypage")
    public String myPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            ArrayList<Loan> loans = loanService.findLoansByUser(user.getId());
            ArrayList<LoanBookDTO> loanBookDTOs = new ArrayList<>();

            for (Loan loan : loans) {
                Book book = bookService.findById(loan.getBookId());
                loanBookDTOs.add(new LoanBookDTO(loan, book));
            }

            String uname = extractUsername(user.getUsername());
            model.addAttribute("uname", uname);
            model.addAttribute("loanBooks", loanBookDTOs);
            model.addAttribute("user", user);
        }


        return "/user/mypage";
    }

    @GetMapping("/user/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/user/login";
    }
}
