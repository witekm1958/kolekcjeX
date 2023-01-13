package pl.wszib.kolekcje.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.wszib.kolekcje.data.entities.User;
import pl.wszib.kolekcje.data.repositories.UserRepository;

import java.util.Optional;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    public static final String NO_USER_FOUND_WITH_USERNAME = "Nie znaleziono takiego użytkownika: ";

    @Autowired
    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> usernameOptional = userRepository.findByUsername(username);
        if(!usernameOptional.isPresent()) {
            throw new UsernameNotFoundException(NO_USER_FOUND_WITH_USERNAME + username);
        }
        User user = usernameOptional.get();
        return user;
    }
}
