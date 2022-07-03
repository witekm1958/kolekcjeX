package pl.wszib.kolekcje.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.wszib.kolekcje.data.entities.ProfileEntity;
import pl.wszib.kolekcje.data.repositories.ProfileRepository;
import pl.wszib.kolekcje.services.ProfileService;
import pl.wszib.kolekcje.web.models.ProfileModel;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;


@Controller
@RequestMapping("register")
public class ProfileController {

    private final ProfileRepository profileRepository;
    private final ProfileService profileService;

    public ProfileController(ProfileRepository profileRepository, ProfileService profileService) {
        this.profileRepository = profileRepository;
        this.profileService = profileService;
    }

    @GetMapping("profile/{profileId}")
    public String showRegisterProfilePage(
            @PathVariable Long profileId,
            @ModelAttribute("profile") ProfileModel profileModel,
            Model model) {
        ProfileEntity profileEntity = profileRepository.findById(profileId).orElseThrow(EntityExistsException::new);
        model.addAttribute("profile", profileEntity);
        return "profilePage";
    }

    @PostMapping("profile{profileId}")
    public String processSaveProfile(
            @PathVariable Long profileId,
            @ModelAttribute("profile") @Valid ProfileModel profileModel,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            ProfileEntity profileEntity = profileRepository.findById(profileId).orElseThrow(EntityNotFoundException::new);
            model.addAttribute("profile", profileEntity);
            return "profilePage";
        }
        profileService.saveProfile(profileId, profileModel);

        return "redirect:/home";
    }



}
