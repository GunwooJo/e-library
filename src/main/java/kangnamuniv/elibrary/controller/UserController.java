package kangnamuniv.elibrary.controller;

import jakarta.servlet.http.HttpSession;
import kangnamuniv.elibrary.dto.UserDTO;
import kangnamuniv.elibrary.entity.User;
import kangnamuniv.elibrary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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
        return "redirect:/home";
    }

    @GetMapping("/user/login")
    public String loginPage(@RequestParam(required = false) String error, Model model) {

        if ("true".equals(error)) {
            model.addAttribute("loginErrorMsg", "존재하지 않는 회원이거나 비밀번호가 일치하지 않습니다.");
        }

        return "/user/login";
    }
}
