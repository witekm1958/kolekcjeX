package pl.wszib.kolekcje.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/login")
public class LoginController {

    public String authenticate() {
        return "loginPage";
    }

}

