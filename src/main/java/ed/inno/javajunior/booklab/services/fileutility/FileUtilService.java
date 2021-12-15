package ed.inno.javajunior.booklab.services.fileutility;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface FileUtilService {
    void saveFileToDisk(MultipartFile multipartFile, int maxSize, String fileName);
}
