package pl.wszib.kolekcje.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.wszib.kolekcje.data.repositories.ProfileRepository;

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

//    @GetMapping("register")
//    public String showUserRegisterPage(
//            @ModelAttribute("users") RegisterModel registerModel,
//            Model model) {
//        return "registerPage";
//    }

}
