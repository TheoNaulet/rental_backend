package com.example.rental_backend.controller;

import com.example.rental_backend.dto.LoginDTO;
import com.example.rental_backend.dto.RegisterDTO;
import com.example.rental_backend.service.JWTService;
import com.example.rental_backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    public JWTService jwtService;

    @Autowired
    public UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterDTO registerDTO) {
        userService.registerUser(registerDTO);
        return "User registered successfully!";
    }


    @PostMapping("/login")
        public String getToken(@RequestBody LoginDTO loginDTO) {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
            return jwtService.generateToken(authenticate);
        }
    }
