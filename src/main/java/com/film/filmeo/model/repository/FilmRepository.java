package com.film.filmeo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.film.filmeo.model.entity.Film;

public interface FilmRepository  extends JpaRepository<Film, Long> {

}
