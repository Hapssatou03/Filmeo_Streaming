package com.film.filmeo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.film.filmeo.model.entity.Film;
import com.film.filmeo.model.repository.FilmRepository;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;
    public List<Film> getAll() {
        return filmRepository.findAll();
    }

    public Film getOne(Long id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film introuvable avec id " + id));
    }

    public Film insert(Film film) {
        return filmRepository.save(film);
    }

    public Film update(Film film) {
        return filmRepository.save(film);
    }

    public void delete(Long id) {
        filmRepository.deleteById(id);
    }
}
