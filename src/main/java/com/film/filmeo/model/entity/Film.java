package com.film.filmeo.model.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String nationalite;
    private Date dateSortie;
    private String duree;
    private String synopsis;
    private Double note;
    private String afficheUrl;

    // Relation ManyToMany avec User (favoris utilisateurs)
    @ManyToMany(mappedBy = "films")
    private Set<User> users = new HashSet<>();
    
    // Relation ManyToOne avec Personnages en tant que réalisateur
    @ManyToOne
    @JoinColumn(name = "realisateur_id")
    private Personnages realisateur;

    // Relation ManyToMany avec Personnages en tant qu'acteurs
    @ManyToMany
    @JoinTable(
        name = "film_acteur",
        joinColumns = @JoinColumn(name = "film_id"),
        inverseJoinColumns = @JoinColumn(name = "personnage_id")
    )
    private Set<Personnages> acteurs = new HashSet<>();

    // Relation OneToMany avec Commentaire
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commentaire> commentaires = new ArrayList<>();
    
    // Relation ManyToMany avec Genre
    @ManyToMany 
    @JoinTable(
        name = "film_genre",
        joinColumns = @JoinColumn(name = "film_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres = new ArrayList<>();
}
