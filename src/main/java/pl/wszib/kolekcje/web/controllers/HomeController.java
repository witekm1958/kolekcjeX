package pl.wszib.kolekcje.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.wszib.kolekcje.data.entities.User;
import pl.wszib.kolekcje.data.repositories.RoleRepository;
import pl.wszib.kolekcje.data.repositories.UserRepository;
import pl.wszib.kolekcje.services.UserService;
import pl.wszib.kolekcje.web.models.UserModel;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class HomeController {

    boolean profileEexists = false;
    boolean loginEexists = false;

    private final UserRepository userRepository;

    private final UserService userService;

    private final RoleRepository roleRepository;

    private static final String THIS_PROFILE_ALREADY_EXISTS = "Taki Profil już istnieje";
    private static final String THIS_LOGIN_ALREADY_EXISTS = "Taki Login już istnieje";
    private static final String PASSWORDS_VARY = "Hasła są różne";

    public HomeController(UserRepository userRepository, UserService userService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public String showHomePage(Model model) {
        model.addAttribute("name", "Jan");
        return "homePage";
    }

    @GetMapping("register")
    public String showUserRegisterPage(
            @ModelAttribute("userModel") UserModel userModel,
            Model model) {
        return "userRegisterPage";
    }

    @PostMapping("register")
    public String saveUserProfile(
            @ModelAttribute("userModel") @Valid UserModel userModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model) {

        Optional<User> userFromDb = userRepository.findByUserProfile(userModel.getUserProfile());
        if (!userFromDb.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", THIS_PROFILE_ALREADY_EXISTS);
            model.addAttribute("errorProfile", THIS_PROFILE_ALREADY_EXISTS);
            return "userRegisterPage";
        }

        Optional<User> loginFromDb = userRepository.findByUsername(userModel.getUsername());
        if (!loginFromDb.isEmpty()) {
            model.addAttribute("errorLogin", THIS_LOGIN_ALREADY_EXISTS);
            return "userRegisterPage";
        }

        if (!userModel.isItTheSamePassword() ) {
            model.addAttribute("errorPassword", PASSWORDS_VARY);
            return "userRegisterPage";
        }

        if (bindingResult.hasErrors()) {
            return "userRegisterPage";
        }

//        Optional<Role> roleOptional = roleRepository.findByName("USER");
//        if (roleOptional.isPresent()) {
//            userModel.getRoles().add(roleOptional.get());
//        }
        userService.saveProfile(userModel);

        return "registerConfirmationPage";
    }

    @GetMapping("login")
    public String showUserLoginPage(
            @ModelAttribute("login") UserModel userModel,
            Model model) {
        return "loginPage";
    }
}
