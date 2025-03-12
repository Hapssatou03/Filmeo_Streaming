package com.film.filmeo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.film.filmeo.model.entity.ConnectedUser;
import com.film.filmeo.model.entity.User;
import com.film.filmeo.model.repository.UserRepository;

@Service
public class ConnectedUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public ConnectedUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String pseudo) {
        User user = userRepository.findByPseudo(pseudo);
        if (user == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé : " + pseudo);
        }
        return new ConnectedUser(user);
    }
}
