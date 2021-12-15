package ed.inno.javajunior.booklab.controllers;

import ed.inno.javajunior.booklab.entities.Author;
import ed.inno.javajunior.booklab.entities.Book;
import ed.inno.javajunior.booklab.repositories.AuthorRepository;
import ed.inno.javajunior.booklab.repositories.BookRepository;
import ed.inno.javajunior.booklab.services.BookService;
import ed.inno.javajunior.booklab.services.NewsService;
import ed.inno.javajunior.booklab.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final NewsService newsService;
    private final BookService bookService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @GetMapping("/")
    public String homePage(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("news", newsService.getLastNews(12));
        model.addAttribute("books", bookService.getLastBooks(10));
        return "index";
    }

    @GetMapping("/book/{id}")
    public String bookPage(@PathVariable("id") Long id, Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        Book bookItem = bookRepository.findById(id).orElseThrow(NoSuchElementException::new);
        model.addAttribute("book", bookItem);
        return "book-page";
    }

    @GetMapping("/author/{id}")
    public String authorPage(@PathVariable("id") Long id, Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        Author author = authorRepository.findById(id).orElseThrow(NoSuchElementException::new);
        model.addAttribute("author", author);
        return "author-page";
    }
}
