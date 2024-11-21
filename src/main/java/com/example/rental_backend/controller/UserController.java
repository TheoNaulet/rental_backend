package com.example.rental_backend.controller;

import com.example.rental_backend.dto.UserDTO;
import com.example.rental_backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/TE")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        System.out.println(id);
        return userService.getUserById(id);
    }
}
