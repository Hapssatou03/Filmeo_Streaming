package com.film.filmeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView home() {
      ModelAndView mav = new ModelAndView();
      mav.setViewName("base");
mav.addObject("content", "home/index");
return mav;
    }
// route pour la

@GetMapping("Films/home")
public ModelAndView filmsHome() {
    ModelAndView mav = new ModelAndView("base");
    mav.addObject("content", "/home");
    return mav;
}




}


