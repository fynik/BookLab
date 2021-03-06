package ed.inno.javajunior.booklab.repositories;

import ed.inno.javajunior.booklab.entities.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findAllByIdNotNullOrderByLastName();
}
