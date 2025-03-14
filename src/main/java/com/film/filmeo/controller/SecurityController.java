package com.film.filmeo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.film.filmeo.model.entity.User;
import com.film.filmeo.model.service.UserService;

import jakarta.validation.Valid;

@Controller
public class SecurityController { 
    @Autowired
    private UserService userService;

    @Autowired
    Validator validator;

    @GetMapping("/connexion")
    public String login(Model model) {
        model.addAttribute("content", "security/connexion");
        return "base";
    }

    @GetMapping("/inscription")
    public String register(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("content", "security/inscription");
        return "base";
    }

    @PostMapping("/inscription")
    public String register(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("content", "security/inscription");
            return "base";
        }
        userService.registerUser(user);
        return "redirect:/connexion";
    }
}
