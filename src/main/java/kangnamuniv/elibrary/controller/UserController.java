package kangnamuniv.elibrary.controller;

import jakarta.servlet.http.HttpSession;
import kangnamuniv.elibrary.dto.UserDTO;
import kangnamuniv.elibrary.entity.User;
import kangnamuniv.elibrary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public String signUp(@ModelAttribute UserDTO userDTO) {

        userService.signUp(userDTO);
        return "redirect:/user/login.html";
    }

    @PostMapping("/user/login")
    public String login(@ModelAttribute UserDTO userDTO, HttpSession session) {

        Optional<User> loggedInUser = userService.login(userDTO);

        if (loggedInUser.isEmpty()) {
            return "redirect:/user/login";
        }
        session.setAttribute("loggedInUser", loggedInUser.get());
        return "redirect:/home";
    }
}
