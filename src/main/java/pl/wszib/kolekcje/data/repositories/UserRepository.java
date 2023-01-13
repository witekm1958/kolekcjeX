package pl.wszib.kolekcje.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.wszib.kolekcje.data.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUserProfile(String user_profile);
    Optional<User> findByUsername(String user_name);
}
