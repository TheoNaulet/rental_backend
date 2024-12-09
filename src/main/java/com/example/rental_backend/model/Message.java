package com.example.rental_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a message in the system.
 */
@Entity
@Table(name = "MESSAGES") // Maps this entity to the "MESSAGES" table in the database
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id; // Unique identifier for the message

    @Column(nullable = false) // Message content must not be null
    private String message; // Content of the message

    @ManyToOne // Many messages can be sent by one user
    @JoinColumn(name = "user_id", nullable = false) // Foreign key to the "users" table
    private User user; // User who sent the message

    @ManyToOne // Many messages can belong to one rental
    @JoinColumn(name = "rental_id", nullable = false) // Foreign key to the "rentals" table
    private Rental rental; // Rental associated with the message

    @Column(name = "created_at", nullable = false) // Maps to "created_at" in the database
    private LocalDateTime createdAt; // Timestamp when the message was created

    // Getters and Setters

    /**
     * Gets the unique identifier for the message.
     * @return the message ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the message.
     * @param id the message ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the content of the message.
     * @return the message content
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the content of the message.
     * @param message the message content
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the user who sent the message.
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who sent the message.
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets the rental associated with the message.
     * @return the rental
     */
    public Rental getRental() {
        return rental;
    }

    /**
     * Sets the rental associated with the message.
     * @param rental the rental
     */
    public void setRental(Rental rental) {
        this.rental = rental;
    }

    /**
     * Gets the creation timestamp of the message.
     * @return the creation timestamp
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the message.
     * @param createdAt the creation timestamp
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
