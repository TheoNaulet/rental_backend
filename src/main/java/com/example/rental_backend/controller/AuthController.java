package com.example.rental_backend.controller;

import com.example.rental_backend.dto.AuthResponseDTO;
import com.example.rental_backend.dto.LoginDTO;
import com.example.rental_backend.dto.RegisterDTO;
import com.example.rental_backend.dto.UserDTO;
import com.example.rental_backend.service.JWTService;
import com.example.rental_backend.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for authentication-related actions such as login, registration, and user retrieval.
 */
@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    public JWTService jwtService;
    public UserService userService;
    private AuthenticationManager authenticationManager;

    /**
     * Constructor injection for required dependencies.
     *
     * @param jwtService              service for JWT management
     * @param userService             service for user management
     * @param authenticationManager   authentication manager for Spring Security
     */
    public AuthController(JWTService jwtService, UserService userService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Endpoint to register a new user and immediately return a JWT token.
     *
     * @param registerDTO the data for user registration
     * @return a ResponseEntity containing the generated JWT token
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> registerUser(@RequestBody RegisterDTO registerDTO) {
        // Register the new user
        userService.registerUser(registerDTO);
    
        // Authenticate the user after registration
        Authentication authenticate = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(registerDTO.getEmail(), registerDTO.getPassword())
        );
    
        // Generate a JWT token
        String token = jwtService.generateToken(authenticate);

        // Return the response with the token
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    /**
     * Endpoint to retrieve the current authenticated user.
     *
     * @param authentication the current authentication object
     * @return a ResponseEntity containing the user's details
     */
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(Authentication authentication) {
        // Retrieve the user's email from the authentication object
        String email = authentication.getName();

        // Fetch the user's details using the email
        UserDTO userResponse = userService.getUserByEmail(email);

        System.out.println("User details: " + userResponse);
        System.out.println("User email: " + userService.getUserByEmail(email));

        // Return the user's details as a response
        return ResponseEntity.ok(userResponse);
    }


    /**
     * Endpoint to log in an existing user and return a JWT token.
     *
     * @param loginDTO the data for user login
     * @return a ResponseEntity containing the generated JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        // Authenticate the user
        Authentication authenticate = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        // Generate a JWT token
        String token = jwtService.generateToken(authenticate);

        // Return the response with the token
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}
