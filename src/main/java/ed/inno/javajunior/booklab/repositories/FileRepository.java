package ed.inno.javajunior.booklab.repositories;

import ed.inno.javajunior.booklab.entities.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<File, Long> {
    File findByStorageName(String fileName);
}
