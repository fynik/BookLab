package ed.inno.javajunior.booklab.repositories;

import ed.inno.javajunior.booklab.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
