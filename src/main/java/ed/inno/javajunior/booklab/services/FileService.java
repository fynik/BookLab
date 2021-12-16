package ed.inno.javajunior.booklab.services;

import ed.inno.javajunior.booklab.entities.File;
import ed.inno.javajunior.booklab.repositories.FileRepository;
import ed.inno.javajunior.booklab.services.fileutility.FileUtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${files.images.storage.path}")
    private String imageStorageFolder;

    private final FileRepository fileRepository;
    private final FileUtilService fileUtilService;

    public Long saveImage(MultipartFile multipartFile, int maxSize) {
        String originalName = multipartFile.getOriginalFilename();
        String extension = originalName != null ? originalName.substring(originalName.lastIndexOf(".")) : "";


        File file = new File();
        file.setOriginalName(originalName);
        file.setMimeType(multipartFile.getContentType());
        file.setUploaded(LocalDateTime.now());
        file.setStorageName(UUID.randomUUID() + extension);
        file.setFileSize(multipartFile.getSize());
        fileRepository.save(file);

        fileUtilService.saveFileToDisk(multipartFile, maxSize, file.getStorageName());

        return file.getId();
    }


    public void addImageToResponse(String fileName, HttpServletResponse response) {
        File file = fileRepository.findByStorageName(fileName);
        response.setContentType(file.getMimeType());
        response.setContentLength(file.getFileSize().intValue());

        try {
            Files.copy(Paths.get(imageStorageFolder, file.getStorageName()), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
