package ed.inno.javajunior.booklab.services.fileutility;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.nio.file.Paths;


@Service
public class FileUtilServiceImpl implements FileUtilService {

    @Value("${files.images.storage.path}")
    private String imageStorageFolder;

    @Override
    public void saveFileToDisk(MultipartFile multipartFile, int maxSize, String fileName) {
        try {
            BufferedImage bufferedImage = checkAndResizeImage(multipartFile, maxSize);
            ImageIO.write(bufferedImage, "png", Paths.get(imageStorageFolder, fileName).toFile());
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage checkAndResizeImage(MultipartFile multipartFile, int maxSize) throws IOException {
        BufferedImage originalImage = ImageIO.read(multipartFile.getInputStream());
        int imageType = originalImage.getType();
        double scale = 1.0 * maxSize / Math.max(originalImage.getHeight(), originalImage.getWidth());
        int targetWidth = originalImage.getWidth();
        int targetHeight = originalImage.getHeight();

        if (scale < 1) {
            targetWidth = (int) (originalImage.getWidth() * scale);
            targetHeight = (int) (originalImage.getHeight() * scale);
        }

        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, imageType);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }
}
