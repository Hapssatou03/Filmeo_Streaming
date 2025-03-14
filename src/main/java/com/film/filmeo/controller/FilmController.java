package com.film.filmeo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.film.filmeo.model.entity.Film;
import com.film.filmeo.model.service.FilmService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    // Afficher tous les films
    @GetMapping
    public String listFilms(Model model) {
        model.addAttribute("films", filmService.getAll());
        return "films/list"; // src/main/resources/templates/films/list.html
    }

    // Afficher le formulaire de création d'un film
    @GetMapping("/new")
    public String createFilmForm(Model model) {
        model.addAttribute("film", new Film());
        return "films/new";// src/main/resources/templates/films/new.html
    }

    // Créer un nouveau film
      @PostMapping("/new")
    public String createFilm(@Valid @ModelAttribute Film film, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "films/form";
        }
        filmService.insert(film);
        return "redirect:/films";
    }

    // Afficher les détails d'un film
    @GetMapping("/{id}")
    public String getFilm(@PathVariable Long id, Model model) {
        model.addAttribute("film", filmService.getOne(id));
        return "films/view";// src/main/resources/templates/films/view.html
    }

    // Afficher le formulaire de modification d'un film
    @GetMapping("/{id}/edit")
    public String editFilmForm(@PathVariable Long id, Model model) {
        model.addAttribute("film", filmService.getOne(id));
        return "films/edit"; // réutilisation du même formulaire pour création et modification
    }

    // Mettre à jour un film
    @PostMapping("/{id}/edit")
    public String updateFilm(@PathVariable Long id, @Valid @ModelAttribute Film film, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "films/form";
        }
        //ID
        film.setId(id);
        filmService.update(film);
        return "redirect:/films";
    }

    // Supprimer un film
    @PostMapping("/{id}/delete")
    public String deleteFilm(@PathVariable Long id) {
        filmService.delete(id);
        return "redirect:/films";
    }
}