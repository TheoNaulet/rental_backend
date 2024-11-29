package com.example.rental_backend.controller;

import com.cloudinary.Cloudinary;
import com.example.rental_backend.dto.LoginDTO;
import com.example.rental_backend.dto.RegisterDTO;
import com.example.rental_backend.repository.RentalRepository;
import com.example.rental_backend.repository.UserRepository;
import com.example.rental_backend.service.JWTService;
import com.example.rental_backend.service.UserService;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    public JWTService jwtService;
    public UserService userService;
    private AuthenticationManager authenticationManager;

    // Constructor injection for dependencies
    public AuthController(JWTService jwtService, UserService userService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterDTO registerDTO) {
        userService.registerUser(registerDTO);
        return "User registered successfully!";
    }

    @GetMapping("/me")
    public Object getCurrentUser(Authentication authentication) {
        String email = authentication.getName(); 
        return userService.getUserByEmail(email);
    }




@PostMapping("/login")
public ResponseEntity<Map<String, String>> getToken(@RequestBody LoginDTO loginDTO) {
    // Authentifie l'utilisateur
    Authentication authenticate = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
    );

    // Génère le token JWT
    String token = jwtService.generateToken(authenticate);

    // Prépare la réponse en format JSON
    Map<String, String> response = new HashMap<>();
    response.put("token", token);

    return ResponseEntity.ok(response);
}
    }
