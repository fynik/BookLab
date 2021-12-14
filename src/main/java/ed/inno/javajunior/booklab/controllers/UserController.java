package ed.inno.javajunior.booklab.controllers;

import ed.inno.javajunior.booklab.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String homePage(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "index";
    }

    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@RequestParam Map<String, String> regParams, Model model) {
        if (!userService.createUser(regParams)) {
            model.addAttribute("errorMessage", "Пользователь с таким именем уже существует");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "profile";
    }

}
