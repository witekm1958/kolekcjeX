package pl.wszib.kolekcje.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.wszib.kolekcje.data.repositories.ProfileRepository;
import pl.wszib.kolekcje.services.ProfileService;


@Controller
@RequestMapping("profile")
public class ProfileController {

    private final ProfileRepository profileRepository;
    private final ProfileService profileService;

    public ProfileController(ProfileRepository profileRepository, ProfileService profileService) {
        this.profileRepository = profileRepository;
        this.profileService = profileService;
    }
}
