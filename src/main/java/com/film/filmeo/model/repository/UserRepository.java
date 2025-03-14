package com.film.filmeo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.film.filmeo.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByPseudo(String pseudo);
     @Query("SELECT u FROM User u LEFT JOIN FETCH u.films WHERE u.pseudo = :pseudo")
    User findByPseudoWithFilms(String pseudo);

}
