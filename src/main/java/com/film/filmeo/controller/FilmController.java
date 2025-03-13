package com.film.filmeo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.film.filmeo.model.entity.Film;
import com.film.filmeo.model.service.FilmService;
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
        return "films/list";
    }

    // Afficher le formulaire de création d'un film
    @GetMapping("/new")
    public String createFilmForm(Model model) {
        model.addAttribute("film", new Film());
        return "films/new";
    }

    // Créer un nouveau film
    @PostMapping
    public String createFilm(@ModelAttribute Film film) {
        filmService.insert(film);
        return "redirect:/films";
    }

    // Afficher les détails d'un film
    @GetMapping("/{id}")
    public String getFilm(@PathVariable Long id, Model model) {
        model.addAttribute("film", filmService.getOne(id));
        return "films/view";
    }

    // Afficher le formulaire de modification d'un film
    @GetMapping("/{id}/edit")
    public String editFilmForm(@PathVariable Long id, Model model) {
        model.addAttribute("film", filmService.getOne(id));
        return "films/edit";
    }

    // Mettre à jour un film
    @PostMapping("/{id}")
    public String updateFilm(@PathVariable Long id, @ModelAttribute Film film) {
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