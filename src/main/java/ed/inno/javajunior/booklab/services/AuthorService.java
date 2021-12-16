package ed.inno.javajunior.booklab.services;

import ed.inno.javajunior.booklab.entities.Author;
import ed.inno.javajunior.booklab.repositories.AuthorRepository;
import ed.inno.javajunior.booklab.repositories.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService {

    private final FileService fileService;
    private final FileRepository fileRepository;
    private final AuthorRepository authorRepository;



    public void createNewAuthor(String firstName, String lastName, String birthYear,
                                MultipartFile file) {
        Long fileIndex = fileService.saveImage(file, 500);
        log.info("Сохранено изображение " + file.getOriginalFilename());

        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBirthYear(birthYear);
        author.setPhoto(fileRepository.findById(fileIndex).orElseThrow(NoSuchElementException::new));
        authorRepository.save(author);
        log.info(String.format("Сохранен автор %s %s", author.getLastName(), author.getFirstName()));
    }

    public void deleteAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(NoSuchElementException::new);
        authorRepository.delete(author);
        log.info(String.format("Удален автор %s %s", author.getLastName(), author.getFirstName()));
    }
}
