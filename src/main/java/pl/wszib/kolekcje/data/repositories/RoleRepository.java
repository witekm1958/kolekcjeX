package pl.wszib.kolekcje.data.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.wszib.kolekcje.data.entities.Role;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
