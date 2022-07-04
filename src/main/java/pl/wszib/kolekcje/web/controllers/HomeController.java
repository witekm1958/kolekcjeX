package pl.wszib.kolekcje.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.wszib.kolekcje.data.entities.ProfileEntity;
import pl.wszib.kolekcje.data.repositories.ProfileRepository;
import pl.wszib.kolekcje.services.ProfileService;
import pl.wszib.kolekcje.web.models.ProfileModel;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Controller
public class HomeController {

    private final ProfileRepository profileRepository;
    private final ProfileService profileService;

    public HomeController(ProfileRepository profileRepository, ProfileService profileService) {
        this.profileRepository = profileRepository;
        this.profileService = profileService;
    }

    @GetMapping
    public String showHomePage(Model model) {
        model.addAttribute("name", "Jan");
        return "homePage";
    }

    @GetMapping("register")
    public String showUserRegisterPage(
            @ModelAttribute("profile") ProfileModel profileModel,
            Model model) {
        return "profilePage";
    }

    @PostMapping("register")
    public String saveUserProfile(
            @ModelAttribute("profile") @Valid ProfileModel profileModel,
            Model model) {
        return "profilePage";
    }

//    @GetMapping("profile/{profileId}")
//    public String showRegisterProfilePage(
//            @PathVariable Long profileId,
//            @ModelAttribute("profile") ProfileModel profileModel,
//            Model model) {
//        ProfileEntity profileEntity = profileRepository.findById(profileId).orElseThrow(EntityExistsException::new);
//        model.addAttribute("profile", profileEntity);
//        return "profilePage";
//    }

//    @PostMapping("profile{profileId}")
//    public String processSaveProfile(
//            @PathVariable Long profileId,
//            @ModelAttribute("profile") @Valid ProfileModel profileModel,
//            BindingResult bindingResult,
//            Model model) {
//        if (bindingResult.hasErrors()) {
//            ProfileEntity profileEntity = profileRepository.findById(profileId).orElseThrow(EntityNotFoundException::new);
//            model.addAttribute("profile", profileEntity);
//            return "profilePage";
//        }
//        profileService.saveProfile(profileId, profileModel);
//
//        return "redirect:/home";
//    }
}
