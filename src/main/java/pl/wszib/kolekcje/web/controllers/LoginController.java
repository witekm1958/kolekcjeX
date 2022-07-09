package pl.wszib.kolekcje.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wszib.kolekcje.data.repositories.ProfileRepository;
import pl.wszib.kolekcje.services.ProfileService;


@Controller
@RequestMapping("/login")
public class LoginController {

//    private final ProfileRepository profileRepository;
//    private final ProfileService profileService;
//
//    public LoginController(ProfileRepository profileRepository, ProfileService profileService) {
//        this.profileRepository = profileRepository;
//        this.profileService = profileService;
//    }

    public String authenticate() {
        return "login";
    }

}
