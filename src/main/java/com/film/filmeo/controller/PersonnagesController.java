package com.film.filmeo.controller;

import com.film.filmeo.model.entity.Personnages;
import com.film.filmeo.model.service.PersonnagesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/personnages")
public class PersonnagesController {

    @Autowired
    private PersonnagesService personnagesService;

    // Afficher tous les personnages avec la structure du layout
    @GetMapping
    public ModelAndView listPersonnages() {
        ModelAndView mav = new ModelAndView("base");
        mav.addObject("personnages", personnagesService.getAll());
        mav.addObject("content", "personnages/list"); // Insérer la vue dans le layout
        return mav;
    }

    // Afficher formulaire pour créer un personnage
    @GetMapping("/new")
    public ModelAndView createPersonnageForm() {
        ModelAndView mav = new ModelAndView("base");
        mav.addObject("personnage", new Personnages());
        mav.addObject("content", "personnages/form");
        return mav;
    }

    // Sauvegarder un nouveau personnage
    @PostMapping("/new")
public ModelAndView createPersonnage(@Valid @ModelAttribute Personnages personnage, BindingResult bindingResult) {
    ModelAndView mav = new ModelAndView("base");
    if (bindingResult.hasErrors()) {
        mav.addObject("content", "personnages/form");
        return mav;
    }
    personnagesService.insert(personnage);
    return new ModelAndView("redirect:/personnages");
}

    // Afficher les détails d'un personnage
    @GetMapping("/{id}")
    public ModelAndView getPersonnage(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("base");
        mav.addObject("personnage", personnagesService.getOne(id));
        mav.addObject("content", "personnages/view");
        return mav;
    }

    // Afficher formulaire de modification
    @GetMapping("/{id}/edit")
    public ModelAndView editPersonnageForm(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("base");
        mav.addObject("personnage", personnagesService.getOne(id));
        mav.addObject("content", "personnages/form");
        return mav;
    }

    // Mettre à jour un personnage
    @PostMapping("/{id}/edit")
public ModelAndView updatePersonnage(@PathVariable Long id, @Valid @ModelAttribute Personnages personnage, BindingResult bindingResult) {
    ModelAndView mav = new ModelAndView("base");
    if (bindingResult.hasErrors()) {
        mav.addObject("content", "personnages/form");
        return mav;
    }
    personnagesService.update(id, personnage);
    return new ModelAndView("redirect:/personnages");
}

    // Supprimer un personnage
    @PostMapping("/{id}/delete")
    public String deletePersonnage(@PathVariable Long id) {
        personnagesService.delete(id);
        return "redirect:/personnages";
    }
}
