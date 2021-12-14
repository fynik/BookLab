package ed.inno.javajunior.booklab.repositories;

import ed.inno.javajunior.booklab.entities.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends CrudRepository<News, Long> {
}
