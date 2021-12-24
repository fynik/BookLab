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
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
public class GeneralController {

    private final UserService userService;
    private final NewsService newsService;
    private final BookService bookService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @GetMapping("/")
    public String homePage(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("news", newsService.getLastNews(10));
        model.addAttribute("books", bookService.getLastBooks(6));
        return "index";
    }

    @GetMapping("/book/{id}")
    public String bookPage(@PathVariable("id") Long id, Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        Book bookItem = bookRepository.findById(id).orElseThrow(NoSuchElementException::new);
        model.addAttribute("book", bookItem);
        model.addAttribute("userHasBook", userService.userHasBook(principal,bookItem));
        return "book-page";
    }

    @GetMapping("/author/{id}")
    public String authorPage(@PathVariable("id") Long id, Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        Author author = authorRepository.findById(id).orElseThrow(NoSuchElementException::new);
        model.addAttribute("author", author);
        return "author-page";
    }

    @GetMapping("/allbooks")
    public String allBooksPage(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("books", bookRepository.findAll());
        return "/books";
    }

    @GetMapping("/bookshelf")
    public String bookshelfPage(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "bookshelf";
    }

    @PostMapping("/book/add_to_user/{book_id}")
    public String addToBookshelf(@PathVariable("book_id") Long bookId, Principal principal) {
        userService.addBookToUser(principal, bookId);
        return "redirect:/bookshelf";
    }

    @PostMapping("/book/remove_from_user/{book_id}")
    public String removeFromBookshelf(@PathVariable("book_id") Long bookId, Principal principal) {
        userService.removeBookFromUser(principal, bookId);
        return "redirect:/bookshelf";
    }
}
