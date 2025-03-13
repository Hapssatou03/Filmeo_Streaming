package com.film.filmeo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.film.filmeo.model.entity.User;
import com.film.filmeo.model.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/user")//localhost:8080/user ça sert faire le mapping de l'url localhost:8080/user. faire un mapping veut dire que l'url localhost:8080/user va être liée à la classe UserController
public class UserController {
@Autowired
private UserService userService;



//Afficher tous les utilisateurs
@GetMapping
public String listUsers(Model model) {
   model.addAttribute("users", userService.getAll());
   return "user/list"; // vue thymeleaf pour afficher la liste des utilisateurs dans l'url
}


// Afficher un utilisateur par son ID
@GetMapping("/{id}")
public String getUser(@PathVariable Long id, Model model) {
    User user = userService.getOne(id);
    model.addAttribute("user", user);
    return "users/view"; // Vue Thymeleaf pour afficher les détails d'un utilisateur
}

 // Afficher le formulaire de création d'un utilisateur
 @GetMapping("/new")
 public String createUserForm(Model model) {
     model.addAttribute("user", new User());
     return "users/new"; // Vue Thymeleaf pour le formulaire de création
 }

 // Créer un nouvel utilisateur
    @PostMapping
    public String createUser(@ModelAttribute User user) {
        userService.insert(user);
        return "redirect:/users"; // Redirige vers la liste des utilisateurs
    }

// Afficher le formulaire de modification d'un utilisateur
@GetMapping("/{id}/edit")
public String editUserForm(@PathVariable Long id, Model model) {
    User user = userService.getOne(id);
    model.addAttribute("user", user);
    return "users/edit"; // Vue Thymeleaf pour le formulaire de modification
}


 // Mettre à jour un utilisateur
 @PostMapping("/{id}")
 public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
     userService.update(user);
     return "redirect:/users"; // Redirige vers la liste des utilisateurs
 }

 // Supprimer un utilisateur
 @PostMapping("/{id}/delete")
 public String deleteUser(@PathVariable Long id) {
     userService.delete(id);
     return "redirect:/users"; // Redirige vers la liste des utilisateurs
 }

}