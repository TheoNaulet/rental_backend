package com.example.rental_backend.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.rental_backend.repository.UserRepository;

// import java.util.HashMap;
// import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Chercher l'utilisateur dans la base de données
        com.example.rental_backend.model.User userEntity = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        System.out.println("User found: " + userEntity.getEmail());


        return User.builder()
            .username(userEntity.getEmail()) // Utilise l'email comme identifiant
            .password(userEntity.getPassword()).build(); // Mot de passe déjà encodé           
    }
}
