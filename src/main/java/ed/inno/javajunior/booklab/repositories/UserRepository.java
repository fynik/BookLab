package ed.inno.javajunior.booklab.repositories;

import ed.inno.javajunior.booklab.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String name);
}
