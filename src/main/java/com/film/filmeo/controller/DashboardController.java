package com.film.filmeo.controller;

import java.security.Principal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.film.filmeo.model.entity.Film;
import com.film.filmeo.model.entity.User;
import com.film.filmeo.model.repository.FilmRepository;
import com.film.filmeo.model.repository.UserRepository;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FilmRepository filmRepository;

    // Page principale du dashboard
    @GetMapping
    public String showDashboard(Model model, Principal principal) {
        if (principal == null) {
            // Si l'utilisateur n'est pas authentifié, redirige vers la page de connexion.
            return "redirect:/connexion";
        }
        String pseudo = principal.getName();
    
        // Utilisation du fetch join pour charger l'utilisateur avec ses films
        User user = userRepository.findByPseudoWithFilms(pseudo);
        if (user == null) {
            throw new RuntimeException("Utilisateur introuvable");
        }
    
        // La collection 'films' est déjà chargée
        Set<Film> favoris = user.getFilms();
    
        model.addAttribute("user", user);
        model.addAttribute("favoris", favoris);
        model.addAttribute("content", "dashboard");  // Indique que le fragment dashboard doit être inclus
        return "base";  // On retourne le layout de base
    }

    // Ajouter un film aux favoris
    @PostMapping("/addFilm")
    public String addFilm(@RequestParam("filmId") Long filmId, Principal principal) {
        String pseudo = principal.getName();
        User user = userRepository.findByPseudo(pseudo);
        if (user == null) {
            throw new RuntimeException("Utilisateur introuvable");
        }
    
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new RuntimeException("Film introuvable"));
    
        // Ajout du film dans le set
        user.getFilms().add(film);
        userRepository.save(user);
    
        return "redirect:/dashboard";
    }

    // Retirer un film des favoris
    @PostMapping("/removeFilm")
    public String removeFilm(@RequestParam("filmId") Long filmId, Principal principal) {
        String pseudo = principal.getName();
        User user = userRepository.findByPseudo(pseudo);
        if (user == null) {
            throw new RuntimeException("Utilisateur introuvable");
        }
    
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new RuntimeException("Film introuvable"));
    
        // Retrait du film
        user.getFilms().remove(film);
        userRepository.save(user);
    
        return "redirect:/dashboard";
    }
}
