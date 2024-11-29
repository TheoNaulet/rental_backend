package com.example.rental_backend.controller;

import com.example.rental_backend.dto.UserDTO;
import com.example.rental_backend.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller for managing user-related operations.
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;


    /**
     * Constructor injection for UserService.
     *
     * @param userService the service handling user operations
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }



    /**
     * Retrieve a user by their ID.
     *
     * @param id the ID of the user
     * @return a ResponseEntity containing the UserDTO or a 404 Not Found status
     */
    @Operation(summary = "Get user by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User found"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        try {
            logger.info("Fetching user with ID: {}", id);

            UserDTO user = userService.getUserById(id);

            if (user != null) {
                logger.info("User found: {}", user);
                return ResponseEntity.ok(user);
            } else {
                logger.warn("User not found with ID: {}", id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Error fetching user with ID: {}", id, e);
            return ResponseEntity.status(500).build();
        }
    }
}
