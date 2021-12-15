package ed.inno.javajunior.booklab.controllers;

import ed.inno.javajunior.booklab.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class FilesController {

    private final FileService fileService;

    @GetMapping("/images/{file:.+}")
    public void getImage(@PathVariable("file") String fileName, HttpServletResponse response) {
        fileService.addImageToResponse(fileName, response);
    }
}
