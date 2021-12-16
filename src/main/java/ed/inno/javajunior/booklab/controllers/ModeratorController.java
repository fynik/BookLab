package ed.inno.javajunior.booklab.controllers;

import ed.inno.javajunior.booklab.repositories.AuthorRepository;
import ed.inno.javajunior.booklab.repositories.BookRepository;
import ed.inno.javajunior.booklab.services.AuthorService;
import ed.inno.javajunior.booklab.services.BookService;
import ed.inno.javajunior.booklab.services.NewsService;
import ed.inno.javajunior.booklab.services.UserService;
import ed.inno.javajunior.booklab.services.fileutility.FileUtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;


@Controller
@RequiredArgsConstructor
public class ModeratorController {

    private final UserService userService;
    private final NewsService newsService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final FileUtilService fileUtil;


    /*
    Работа с новостями
     */
    @GetMapping("/moder/news_add")
    public String createNews(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "news-add";
    }

    @PostMapping("/moder/news_add")
    public String submitNews(@RequestParam("text") String text,
                             @RequestParam("file") MultipartFile multipartFile,
                             Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        if (!fileUtil.checkFileTypeIsImage(multipartFile)) {
            return "redirect:/error-file";
        }
        if (!multipartFile.isEmpty()) {
            newsService.createNewsItem(text, multipartFile, principal);
        } else {
            newsService.createNewsItem(text, principal);
        }
        return "redirect:/profile";
    }

    @PostMapping("/moder/del_news_item/{id}")
    public String deleteNews(@PathVariable("id") Long id) {
        newsService.deleteNewsItem(id);
        return "redirect:/profile";
    }

    /*
    Работа с книгами
     */
    @GetMapping("/moder/book_add")
    public String createBook(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("authors", authorRepository.findAll());
        return "book-add";
    }

    @PostMapping("/moder/book_add")
    public String submitBook(@RequestParam("title") String title,
                             @RequestParam("description") String description,
                             @RequestParam("pub_year") String pub_year,
                             @RequestParam("author") Long id,
                             @RequestParam("file") MultipartFile multipartFile,
                             Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        if (!fileUtil.checkFileTypeIsImage(multipartFile)) {
            return "redirect:/error-file";
        }
        bookService.createNewBook(title, description, pub_year, id, multipartFile);
        return "redirect:/profile";
    }

    @GetMapping("/moder/book_del")
    public String deleteBookPage(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("books", bookRepository.findAll());
        return "book-del";
    }

    @PostMapping("/moder/book_del/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        return "redirect:/moder/book_del";
    }

    /*
    Работа с авторами
     */
    @GetMapping("/moder/author_add")
    public String createAuthor(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "author-add";
    }

    @PostMapping("/moder/author_add")
    public String submitAuthor(@RequestParam("fname") String firstName,
                               @RequestParam("lname") String lastName,
                               @RequestParam("birth_year") String birthYear,
                               @RequestParam("file") MultipartFile multipartFile,
                               Principal principal, Model model) {
        if (!fileUtil.checkFileTypeIsImage(multipartFile)) {
            return "redirect:/error-file";
        }
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        authorService.createNewAuthor(firstName, lastName, birthYear, multipartFile);
        return "redirect:/profile";
    }

    @GetMapping("/moder/author_del")
    public String deleteAuthorPage(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("authors", authorRepository.findAll());
        return "author-del";
    }

    @PostMapping("/moder/author_del/{id}")
    public String deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthorById(id);
        return "redirect:/moder/author_del";
    }

    @GetMapping("/error-file")
    public String fileError(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "error-file";
    }
}
