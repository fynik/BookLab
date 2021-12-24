package ed.inno.javajunior.booklab.services;

import ed.inno.javajunior.booklab.entities.Book;
import ed.inno.javajunior.booklab.entities.User;
import ed.inno.javajunior.booklab.repositories.BookRepository;
import ed.inno.javajunior.booklab.repositories.RoleRepository;
import ed.inno.javajunior.booklab.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BookRepository bookRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        String name = principal.getName();
        return userRepository.findByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь '%s' не найден", name)));

    }

    public boolean createUser(Map<String, String> regParams) {
        String username = regParams.get("username");
        String email = regParams.get("email");
        if (userRepository.findByUsername(username).isPresent()) {
            return false;
        }
        if (userRepository.findByEmail(email).isPresent()) {
            return false;
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(regParams.get("email"));
        user.setPassword(passwordEncoder.encode(regParams.get("password")));
        user.setActive(true);
        user.getRoles().add(roleRepository.findByName("ROLE_USER").
                orElseThrow(() -> new NoSuchElementException("В системе не установлена роль пользователя")));
        userRepository.save(user);
        log.info("Пользователь " + username + " создан");
        return true;
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        userRepository.delete(user);
        log.info("Пользователь " + user.getUsername() + " удален");
    }

    public void changeActiveStatus(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        user.setActive(!user.getActive());
        userRepository.save(user);
    }

    public void addBookToUser(Principal principal, Long bookId) {
        User user = getUserByPrincipal(principal);
        Book book = bookRepository.findById(bookId).orElseThrow(NoSuchElementException::new);
        user.getBookshelf().add(book);
        userRepository.save(user);
        log.info(String.format("На полку пользователя %s добавлена книга %s", user.getUsername(), book.getTitle()));
    }

    public void removeBookFromUser(Principal principal, Long bookId) {
        User user = getUserByPrincipal(principal);
        Book book = bookRepository.findById(bookId).orElseThrow(NoSuchElementException::new);
        user.getBookshelf().remove(book);
        userRepository.save(user);
        log.info(String.format("С полки пользователя %s удалена книга %s", user.getUsername(), book.getTitle()));
    }

    public Boolean userHasBook(Principal principal, Book book) {
        User user = getUserByPrincipal(principal);
        return user.getBookshelf().contains(book);
    }
}
