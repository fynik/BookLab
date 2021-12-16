package ed.inno.javajunior.booklab.services;

import ed.inno.javajunior.booklab.entities.News;
import ed.inno.javajunior.booklab.entities.User;
import ed.inno.javajunior.booklab.repositories.FileRepository;
import ed.inno.javajunior.booklab.repositories.NewsRepository;
import ed.inno.javajunior.booklab.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;
    private final FileService fileService;
    private final FileRepository fileRepository;


    public void createNewsItem(String text, MultipartFile file, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(NoSuchElementException::new);
        Long fileIndex = fileService.saveImage(file, 250);
        log.info("Сохранено изображение " + file.getOriginalFilename());

        News newsItem = new News();
        newsItem.setContent(text);
        newsItem.setPublished(LocalDateTime.now());
        newsItem.setUser(user);
        newsItem.setPicture(fileRepository.findById(fileIndex).orElseThrow(NoSuchElementException::new));
        newsRepository.save(newsItem);
        log.info("Сохранена новость под ID" + newsItem.getId() + " для пользователя " + user.getUsername());
    }

    public void createNewsItem(String text, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(NoSuchElementException::new);

        News newsItem = new News();
        newsItem.setContent(text);
        newsItem.setPublished(LocalDateTime.now());
        newsItem.setUser(user);
        newsRepository.save(newsItem);
        log.info("Сохранена новость без изображения под ID" + newsItem.getId() + " для пользователя " + user.getUsername());
    }

    public void deleteNewsItem(Long id) {
        News newsItem = newsRepository.findById(id).orElseThrow(NoSuchElementException::new);
        newsRepository.delete(newsItem);
        log.info("Новость с ID" + newsItem.getId() + " удалена");
    }


    public List<News> getLastNews(int amountOfItems) {
        long totalAmountOfNewsItems = StreamSupport.stream(newsRepository.findAll().spliterator(), true).count();
        if (amountOfItems > (int) totalAmountOfNewsItems) {
            amountOfItems = (int) totalAmountOfNewsItems;
        }
        return newsRepository.findAllByIdNotNullOrderByPublishedDesc().subList(0, amountOfItems);
    }
}
