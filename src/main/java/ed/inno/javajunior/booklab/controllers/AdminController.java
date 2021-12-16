package ed.inno.javajunior.booklab.controllers;

import ed.inno.javajunior.booklab.repositories.UserRepository;
import ed.inno.javajunior.booklab.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/admin")
    private String listUsers(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("userlist", userRepository.findAll());
        return "admin";
    }

    @PostMapping("/admin/user_del/{id}")
    private String deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/admin";
    }
}