package pl.wszib.kolekcje.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("register/confirmation")
public class RegisterConfirmationController {

    @GetMapping
    public String showConfirmationPage(Model model) {
        return "registerConfirmationPage";
    }
}
