package ed.inno.javajunior.booklab.services;

import ed.inno.javajunior.booklab.entities.File;
import ed.inno.javajunior.booklab.repositories.FileRepository;
import ed.inno.javajunior.booklab.services.fileutility.FileUtilService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {


    private final FileRepository fileRepository;
    private final FileUtilService fileUtilService;

    public Long saveImage(MultipartFile multipartFile, int maxSize) {
        String originalName = multipartFile.getOriginalFilename();
        assert originalName != null;
        String extension = originalName.substring(originalName.lastIndexOf("."));


        File fileEntity = new File();
        fileEntity.setOriginalName(originalName);
        fileEntity.setMimeType(multipartFile.getContentType());
        fileEntity.setUploaded(LocalDateTime.now());
        fileEntity.setStorageName(UUID.randomUUID() + extension);
        fileRepository.save(fileEntity);

        fileUtilService.saveFileToDisk(multipartFile, maxSize, fileEntity.getStorageName());

        return fileEntity.getId();
    }



}
