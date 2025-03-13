package com.film.filmeo.model.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Personnages {
@Id
private Long id;
private String nom;
private String prenom;
private Date naissance;
private String lieuNaissance;
private String photo;
private Date deces;
private String nationalite;
private String biographie;
private String sexe;
private String metier;


// Films réalisés par cette personne (en tant que réalisateur)
    @OneToMany(mappedBy = "realisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Film> filmsRealises = new HashSet<>();

    // Films dans lesquels cette personne a joué (en tant qu'acteur)
    @ManyToMany(mappedBy = "acteurs")
    private Set<Film> filmsJoues = new HashSet<>();

    //pour definir des collections selon le rôle (réalisateur, acteur,)

}



