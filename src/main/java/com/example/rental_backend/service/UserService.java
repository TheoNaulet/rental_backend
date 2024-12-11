// Service for managing user-related operations
package com.example.rental_backend.service;

import com.example.rental_backend.dto.RegisterDTO;
import com.example.rental_backend.dto.UserDTO;
import com.example.rental_backend.model.User;
import com.example.rental_backend.repository.UserRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for managing user-related operations.
 * This service acts as a bridge between the controllers and the repositories.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for injecting dependencies.
     * @param userRepository the repository for user data
     * @param passwordEncoder the encoder for securely storing passwords
     */
    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user in the system.
     * @param registerDTO the data transfer object containing user registration details
     * @return the saved User entity
     */
    public User registerUser(RegisterDTO registerDTO) {
        // Create a new User object and populate it with data from the DTO
        User user = new User();
        user.setName(registerDTO.getName());
        user.setEmail(registerDTO.getEmail());
        
        // Encode the password for security
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        // Set creation and update timestamps
        user.setCreatedAt(LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault()));
        user.setUpdatedAt(LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault()));

        // Save the user in the database
        return userRepository.save(user);
    }

    /**
     * Retrieves a user's details by their email.
     * @param email the email address of the user
     * @return a UserDTO containing the user's details
     * @throws RuntimeException if the user is not found
     */
    public UserDTO getUserByEmail(String email) {
        // Fetch the user by email, throw an exception if not found
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Convert the User entity to a UserDTO
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt());
    }

    /**
     * Retrieves a user's details by their ID.
     * @param id the ID of the user
     * @return a UserDTO containing the user's details
     * @throws RuntimeException if the user is not found
     */
    public UserDTO getUserById(Long id) {
        // Fetch the user by ID, throw an exception if not found
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Convert the User entity to a UserDTO
        return new UserDTO(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }
}
