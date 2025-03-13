package com.film.filmeo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.film.filmeo.model.service.ConnectedUserService;

@Configuration
public class SecurityConfig {
    
    @Autowired
    ConnectedUserService connectedUserService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = 
        httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
    
    authenticationManagerBuilder
        .userDetailsService(connectedUserService)
        .passwordEncoder(passwordEncoder());

    return authenticationManagerBuilder.build();
}

@Bean
SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/admin/**").hasRole("ADMIN") // Accessible uniquement aux ADMIN
            .requestMatchers("/mod/**").hasAnyRole("ADMIN", "MODERATOR") // Accessible aux ADMIN et MODERATOR
            .requestMatchers("/watcher/**").hasRole("USER") // Accessible aux USER
            .anyRequest().permitAll() // Toutes les autres routes sont publiques
        )
        .csrf(csrf -> csrf.disable()) // Désactivation de CSRF
        .formLogin(form -> form
            .loginPage("/connexion")
            .loginProcessingUrl("/processLogin")
            .defaultSuccessUrl("/dashboard", true) 
            .permitAll()
            )
        .logout(logout -> logout
            .logoutUrl("/deconnexion")
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true) // la session utilisateur n'est plus valide 
            .deleteCookies("JSESSIONID") //supprime le cookie de session du client
            .permitAll()
        )
        .sessionManagement(session -> session
            .maximumSessions(1) // Une seule session active par utilisateur
        );

    return httpSecurity.build();
}

}