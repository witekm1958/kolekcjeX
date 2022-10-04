package pl.wszib.kolekcje.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wszib.kolekcje.data.entities.User;
import pl.wszib.kolekcje.data.repositories.RoleRepository;
import pl.wszib.kolekcje.data.repositories.UserRepository;
import pl.wszib.kolekcje.web.mappers.UserMapper;
import pl.wszib.kolekcje.web.models.UserModel;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Date;

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

        User userEntity = UserMapper.toEntity(userModel);

        userEntity.setDateOfRegistration(date);

//        roleRepository.findByName("USER").ifPresent(role ->  userModel.setRoles(Collections.singleton(role)));
        roleRepository.findByName("USER").ifPresent(role ->  userEntity.setRoles(Collections.singleton(role)));

        userRepository.save(userEntity);

    }

}
