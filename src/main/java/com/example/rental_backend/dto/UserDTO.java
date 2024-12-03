package com.example.rental_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

/**
 * DTO for transferring user information.
 */
public class UserDTO {
    
    private Long id; // Unique identifier for the user
    
    private String email; // User's email address
    
    private String name; // User's name

    @JsonProperty("created_at") // Maps JSON "created_at" to the Java field "createdAt"
    private LocalDateTime createdAt; // Timestamp when the user was created

    @JsonProperty("updated_at") // Maps JSON "updated_at" to the Java field "updatedAt"
    private LocalDateTime updatedAt; // Timestamp when the user was last updated

    /**
     * Constructor for UserDTO.
     * 
     * @param id the unique identifier for the user
     * @param name the user's name
     * @param email the user's email address
     * @param createdAt the creation timestamp
     * @param updatedAt the last update timestamp
     */
    public UserDTO(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier for the user.
     * @return the user ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the user.
     * @param id the user ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the user's email address.
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     * @param email the user's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's name.
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     * @param name the user's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the creation timestamp.
     * @return the creation timestamp
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp.
     * @param createdAt the creation timestamp
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the last update timestamp.
     * @return the last update timestamp
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the last update timestamp.
     * @param updatedAt the last update timestamp
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
