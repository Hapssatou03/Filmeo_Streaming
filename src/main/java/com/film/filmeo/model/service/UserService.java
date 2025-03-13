package com.film.filmeo.model.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.film.filmeo.model.entity.User;
import com.film.filmeo.model.repository.UserRepository;

@Service
public class UserService {

    // @Autowired
    // UserRepository userRepository;

    // @Autowired
    // PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
  
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAll() { 
        return userRepository.findAll();
    }
    
    public User getOne(long id) { 
        return userRepository.findById(id).orElse(null);
    }

    public User insert(User user) { 
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public User update(User user) { 
        User oldUser = userRepository.findById(user.getId()).orElse(null);
        if (!oldUser.getPassword().equals(user.getPassword()) ) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
        
    }
    public User registerUser(User user) { 
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword); // Il faut mettre à jour le mot de passe encodé
        user.getRoles().add("ROLE_USER"); 
        return userRepository.save(user);
    }
    

    public void delete(long id) { 
        userRepository.deleteById(id);
    }
}
