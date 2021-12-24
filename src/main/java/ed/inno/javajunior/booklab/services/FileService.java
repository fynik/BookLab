package ed.inno.javajunior.booklab.services;

import ed.inno.javajunior.booklab.entities.File;
import ed.inno.javajunior.booklab.repositories.FileRepository;
import ed.inno.javajunior.booklab.services.fileutility.FileUtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

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
        log.info("Файл " + originalName + " записан");
        return file.getId();
    }
}