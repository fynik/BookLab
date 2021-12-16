package ed.inno.javajunior.booklab.repositories;

import ed.inno.javajunior.booklab.entities.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAllByIdNotNullOrderByAdditionDateDesc();
}
