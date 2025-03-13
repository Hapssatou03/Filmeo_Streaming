package com.film.filmeo.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PlateformeStreaming {
@Id
private Long id;
private String name;
}
