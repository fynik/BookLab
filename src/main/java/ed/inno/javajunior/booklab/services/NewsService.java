package ed.inno.javajunior.booklab.services;

import ed.inno.javajunior.booklab.entities.News;
import ed.inno.javajunior.booklab.entities.User;
import ed.inno.javajunior.booklab.repositories.FileRepository;
import ed.inno.javajunior.booklab.repositories.NewsRepository;
import ed.inno.javajunior.booklab.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;
    private final FileService fileService;
    private final FileRepository fileRepository;


    public void createNewsItem(String text, MultipartFile file, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get(); //Юзер гарантированно есть, т.к. залогинен
        Long fileIndex = fileService.saveImage(file, 250);

        News newsItem = new News();
        newsItem.setContent(text);
        newsItem.setPublished(LocalDateTime.now());
        newsItem.setUser(user);
        newsItem.setPicture(fileRepository.findById(fileIndex).orElseThrow(NoSuchElementException::new));
        newsRepository.save(newsItem);
    }
}
