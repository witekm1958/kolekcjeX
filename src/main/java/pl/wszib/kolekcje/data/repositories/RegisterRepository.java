package pl.wszib.kolekcje.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wszib.kolekcje.data.entities.User;

@Repository
public interface RegisterRepository extends JpaRepository<User, Long> {
}
