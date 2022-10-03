package pl.wszib.kolekcje.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wszib.kolekcje.data.entities.Role;
import pl.wszib.kolekcje.data.entities.User;
import pl.wszib.kolekcje.data.repositories.RoleRepository;
import pl.wszib.kolekcje.data.repositories.UserRepository;
import pl.wszib.kolekcje.web.mappers.UserMapper;
import pl.wszib.kolekcje.web.models.UserModel;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    Date date = new Date();

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void saveProfile(UserModel userModel) {

//        Optional<Role> roleOptional = roleRepository.findByName("USER");
//        if (roleOptional.isPresent()) {
//            System.out.println("PRZED ZAPISAM ROLI!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + roleOptional.toString());
//            userModel.getRoles().add(roleOptional.get());
//            System.out.println("NIE ZAPISAL ROLI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        }

        User userEntity = UserMapper.toEntity(userModel);

        userEntity.setDateOfRegistration(date);

        Optional<Role> roleOptional = roleRepository.findByName("USER");
        if (roleOptional.isPresent()) {
            userModel.getRoles().add(roleOptional.get());
        }

        userRepository.save(userEntity);

    }

}
