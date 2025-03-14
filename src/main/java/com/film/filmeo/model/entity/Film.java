package com.film.filmeo.model.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String nationalite;
    private String dateSortie;

    @NotNull(message = "La note est obligatoire")
    private Double note;

    //  afficheUrl
    private String afficheUrl;

    // Relation ManyToMany avec User
    @ManyToMany(mappedBy = "films")
    private Set<User> users = new HashSet<>();
    
// Ajout de la relation ManyToOne pour le réalisateur
    @ManyToOne
    @JoinColumn(name = "realisateur_id")
    private Personnages realisateur;

    // Relation ManyToMany avec Personnes en tant qu'acteurs
    @ManyToMany
    @JoinTable(
        name = "film_acteur",
        joinColumns = @JoinColumn(name = "film_id"),
        inverseJoinColumns = @JoinColumn(name = "personnes_id")
    )
    private Set<Personnages> acteurs = new HashSet<>();

    // Relation OneToMany avec Commentaire
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true) //
    private List<Commentaire> commentaires = new ArrayList<>();
    
    // Relation ManyToMany avec Genre
    @ManyToMany 
    private List<Genre> genres = new ArrayList<>();

}






