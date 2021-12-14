package ed.inno.javajunior.booklab.controllers;

import ed.inno.javajunior.booklab.services.fileutility.FileUtilService;
import ed.inno.javajunior.booklab.services.NewsService;
import ed.inno.javajunior.booklab.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ModeratorController {

    private final UserService userService;
    private final NewsService newsService;
    private final FileUtilService fileUtil;



    @GetMapping("/moder/news_add")
    public String createNews(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "news_add";
    }

    @PostMapping("/moder/news_add")
    public String createNews(@RequestParam("text") String text,
                             @RequestParam("file") MultipartFile multipartFile,
                             Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));

        if (!fileUtil.checkFileTypeIsImage(multipartFile)) {
            model.addAttribute("errorMessage", "Вы пытаетесь загрузить файл неправильного формата");
            return "news_add";
        }
        newsService.createNewsItem(text, multipartFile, principal);
        return "redirect:/profile";
    }

}
