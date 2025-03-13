package com.film.filmeo.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCommentaire;

    private String contenu;
    private java.util.Date date; // ou LocalDate/LocalDateTime
    private Integer note;

    // Liaison avec User : plusieurs commentaires pour un user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Liaison avec Film 
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    //  liaison avec Serie
    // @ManyToOne
    // @JoinColumn(name = "serie_id")
    // private Serie serie;
}
