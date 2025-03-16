package com.film.filmeo.model.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Personnages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private Date dateNaissance;
    private Date deces;
    private String nationalite;
    private String biographie;
    private String sexe;
    private String photoUrl;
    private String metier;

    // Films réalisés par cette personne (en tant que réalisateur)
    @OneToMany(mappedBy = "realisateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Film> filmsRealises = new HashSet<>();

    // Relation ManyToMany : Films dans lesquels ce personnage est acteur
    @ManyToMany(mappedBy = "acteurs")
    private Set<Film> filmsActeurs = new HashSet<>();

}
