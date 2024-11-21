package com.example.rental_backend.controller;

import com.example.rental_backend.dto.LoginDTO;
import com.example.rental_backend.dto.RegisterDTO;
import com.example.rental_backend.model.User;
import com.example.rental_backend.repository.UserRepository;
import com.example.rental_backend.service.JWTService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    public JWTService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterDTO registerDTO) {
        // Crée un nouvel utilisateur
        User user = new User();
        user.setName(registerDTO.getName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(registerDTO.getPassword()));

        userRepository.save(user);

        return "User registered successfully!";
    }

    //REQUESTBODY LoginDTO
    @PostMapping("/login")
        public String getToken(@RequestBody LoginDTO loginDTO) {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
            return jwtService.generateToken(authenticate);
        }
    }
