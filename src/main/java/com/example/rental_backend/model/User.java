package com.example.rental_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a user in the system.
 */
@Entity
@Table(name = "USERS") // Maps this entity to the "users" table in the database
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id; // Unique identifier for the user

    @Column(nullable = false, unique = true) // Email must be unique and not null
    private String email; // User's email address

    @Column(nullable = false) // Name must not be null
    private String name; // User's name

    @Column(nullable = false) // Password must not be null
    private String password; // User's hashed password

    @Column(name = "created_at", nullable = false, updatable = false) // Maps to "created_at" in the database
    private LocalDateTime createdAt; // Timestamp for when the user was created

    @Column(name = "updated_at", nullable = false) // Maps to "updated_at" in the database
    private LocalDateTime updatedAt; // Timestamp for when the user was last updated

    // Lifecycle Callbacks

    /**
     * Automatically sets the creation and update timestamps before persisting.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Automatically sets the update timestamp before updating.
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
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
     * Gets the user's hashed password.
     * @return the user's hashed password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's hashed password.
     * @param password the user's hashed password
     */
    public void setPassword(String password) {
        this.password = password;
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
