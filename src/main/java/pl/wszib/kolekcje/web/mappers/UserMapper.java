package pl.wszib.kolekcje.web.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import pl.wszib.kolekcje.data.entities.User;
import pl.wszib.kolekcje.data.repositories.RoleRepository;
import pl.wszib.kolekcje.data.repositories.UserRepository;
import pl.wszib.kolekcje.web.models.UserModel;

public class UserMapper {

    private UserRepository userRepository;
    private RoleRepository roleRepository;


    @Autowired
    public UserMapper(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public static User toEntity(UserModel model) {
        User entity = new User();
        entity.setUserProfile(model.getUserProfile());
        entity.setUsername(model.getUsername());
        entity.setPassword(model.getPassword());
        entity.setAddressEmail(model.getAddressEmail());
        entity.setRoles(model.getRoles());
        return entity;
    }

}