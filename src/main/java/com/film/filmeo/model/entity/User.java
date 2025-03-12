package com.film.filmeo.model.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "pseudo"),
                            @UniqueConstraint(columnNames = "email") })

public class User {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false, unique = true)
//contrainte sur les champs : règle de validation
private String pseudo;

@Column(nullable = false, unique = true)
private String email;

@Column(nullable = false)
@NotBlank(message = "Le mot de passe ne peut pas être vide")
private String password;

//donne une information sur ce champs
//fetch: récupéré
@ElementCollection(fetch = FetchType.EAGER)
@UniqueElements(message = "Cet utilisateur a des rôles en doublons")
private List<String> roles = new ArrayList<>();
}
