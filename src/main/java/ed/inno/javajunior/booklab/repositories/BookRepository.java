package ed.inno.javajunior.booklab.repositories;

import ed.inno.javajunior.booklab.entities.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
