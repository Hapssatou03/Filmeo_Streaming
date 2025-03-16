package com.film.filmeo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.film.filmeo.model.entity.Personnages;

public interface PersonnageRepository extends JpaRepository<Personnages, Long> {

}
