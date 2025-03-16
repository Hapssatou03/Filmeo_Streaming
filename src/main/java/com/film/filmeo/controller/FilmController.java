package com.film.filmeo.controller;

import com.film.filmeo.model.entity.Film;
import com.film.filmeo.model.service.FilmService;
import com.film.filmeo.model.service.PersonnagesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private PersonnagesService personnagesService;

    // Page d'accueil stylisée avec boutons
    @GetMapping("/home")
    public ModelAndView filmsHome() {
        ModelAndView mav = new ModelAndView("base");
        mav.addObject("content", "Films/home");
        return mav;
    }

    // Afficher tous les films
    @GetMapping
    public ModelAndView listFilms() {
        ModelAndView mav = new ModelAndView("base");
        mav.addObject("films", filmService.getAll());
        mav.addObject("content", "Films/list");
        return mav;
    }

    // Afficher formulaire de création d'un film
    @GetMapping("/new")
    public ModelAndView createFilmForm() {
        ModelAndView mav = new ModelAndView("base");
        mav.addObject("film", new Film());
        mav.addObject("personnages", personnagesService.getAll());
        mav.addObject("content", "Films/form");
        return mav;
    }

    // Créer un nouveau film
    @PostMapping("/new")
    public ModelAndView createFilm(@Valid @ModelAttribute Film film, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("base");
            mav.addObject("personnages", personnagesService.getAll());
            mav.addObject("content", "Films/form");
            return mav;
        }
        filmService.insert(film);
        return new ModelAndView("redirect:/films");
    }

    // Afficher détails d'un film
    @GetMapping("/{id}")
    public ModelAndView getFilm(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("base");
        mav.addObject("film", filmService.getOne(id));
        mav.addObject("content", "Films/view");
        return mav;
    }

    // Afficher formulaire modification
    @GetMapping("/{id}/edit")
    public ModelAndView editFilmForm(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("base");
        mav.addObject("film", filmService.getOne(id));
        mav.addObject("personnages", personnagesService.getAll());
        mav.addObject("content", "Films/form");
        return mav;
    }

    // Mettre à jour un film
    @PostMapping("/{id}/edit")
    public String updateFilm(@PathVariable Long id, @Valid @ModelAttribute Film film, BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("personnages", personnagesService.getAll());
            return "Films/form";
        }
        filmService.update(id, film);
        return "redirect:/films";
    }

    // Supprimer un film
    @PostMapping("/{id}/delete")
    public String deleteFilm(@PathVariable Long id) {
        filmService.delete(id);
        return "redirect:/films";
    }
}
