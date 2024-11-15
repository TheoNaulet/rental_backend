package com.example.rental_backend.controller;

import com.example.rental_backend.dto.RegisterDTO;
import com.example.rental_backend.model.User;
import com.example.rental_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterDTO registerDTO) {
        // Crée un nouvel utilisateur
        User user = new User();
        user.setName(registerDTO.getName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword()); // Pense à sécuriser le mot de passe avec un hash

        // Sauvegarde l'utilisateur dans la base de données
        userRepository.save(user);

        return "User registered successfully!";
    }

    
}
