package ed.inno.javajunior.booklab.repositories;

import ed.inno.javajunior.booklab.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String name);
    Optional<User> findByEmail(String name);
    List<User> findAllByIdNotNullOrderByIdAsc();
}
