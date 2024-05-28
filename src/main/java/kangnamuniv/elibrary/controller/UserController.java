package kangnamuniv.elibrary.controller;

import kangnamuniv.elibrary.dto.UserDTO;
import kangnamuniv.elibrary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public String signUp(@ModelAttribute UserDTO userDTO) {

        userService.signUp(userDTO);
        return "redirect:/user/login";
    }
}
