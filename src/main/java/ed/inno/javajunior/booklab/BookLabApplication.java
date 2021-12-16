package ed.inno.javajunior.booklab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BookLabApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BookLabApplication.class, args);
    }

}
