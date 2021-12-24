package ed.inno.javajunior.booklab.services;

import ed.inno.javajunior.booklab.entities.Book;
import ed.inno.javajunior.booklab.repositories.AuthorRepository;
import ed.inno.javajunior.booklab.repositories.BookRepository;
import ed.inno.javajunior.booklab.repositories.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final FileService fileService;
    private final FileRepository fileRepository;
    private final AuthorRepository authorRepository;


    public List<Book> getLastBooks(int amountOfItems) {
        long totalAmountOfNewsBooks = StreamSupport.stream(bookRepository.findAll().spliterator(), true).count();
        if (amountOfItems > (int) totalAmountOfNewsBooks) {
            amountOfItems = (int) totalAmountOfNewsBooks;
        }
        return bookRepository.findAllByIdNotNullOrderByAdditionDateDesc().subList(0, amountOfItems);
    }

    public void createNewBook(String title, String description, String pub_year,
                              Long authorId, MultipartFile file) {
        Long fileIndex = fileService.saveImage(file, 500);
        log.info("Сохранено изображение " + file.getOriginalFilename());

        Book book = new Book();
        book.setTitle(title);
        book.setAdditionDate(LocalDateTime.now());
        book.setDescription(description);
        book.setCover(fileRepository.findById(fileIndex).orElseThrow(NoSuchElementException::new));
        book.setPublishedYear(pub_year);
        book.setAuthor(authorRepository.findById(authorId).orElseThrow(NoSuchElementException::new));
        bookRepository.save(book);
        log.info("Сохранена книга \"" + book.getTitle() + "\"");
    }

    public void deleteBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(NoSuchElementException::new);
        bookRepository.delete(book);
        log.info("Книга \"" + book.getTitle() + "\" удалена");
    }
}
