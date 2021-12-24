package ed.inno.javajunior.booklab.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;


@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${files.images.storage.path}")
    private String imageStorageFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**", "/images/**")
                .addResourceLocations("classpath:/static/", Paths.get(imageStorageFolder).toUri().toString());

    }



}