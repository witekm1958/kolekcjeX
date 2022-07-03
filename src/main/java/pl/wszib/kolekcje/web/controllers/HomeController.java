package pl.wszib.kolekcje.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import pl.wszib.kolekcje.data.entities.ProfileEntity;
import pl.wszib.kolekcje.data.repositories.ProfileRepository;
import pl.wszib.kolekcje.web.models.ProfileModel;

import javax.persistence.EntityExistsException;

@Controller
public class HomeController {

    private final ProfileRepository profileRepository;

    public HomeController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @GetMapping
    public String showHomePage(Model model) {
        model.addAttribute("name", "Jan");
        return "homePage";
    }

    @GetMapping("register")
    public String showUserRegisterPage(
            @ModelAttribute("users") ProfileModel profileModel,
            Model model) {
        return "profilePage";
    }

}
