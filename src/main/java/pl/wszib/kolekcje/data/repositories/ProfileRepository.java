package pl.wszib.kolekcje.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wszib.kolekcje.data.entities.ProfileEntity;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    // dopisalem 12 i 13 linie
    Optional<ProfileEntity> findByUserName(String user_name);
    Optional<ProfileEntity> findByLoginName(String login_name);
}
