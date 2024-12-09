package com.example.rental_backend.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO for transferring message information between the client and the backend.
 */
public class MessageDTO {

    private Long id; // Unique identifier for the message

    @NotBlank
    private String message; // Content of the message

    @JsonProperty("user_id") // Maps "user_id" in JSON to the "userId" field in Java
    private Long userId;

    @JsonProperty("rental_id") // Maps "rental_id" in JSON to the "rentalId" field in Java
    private Long rentalId; // Rental ID associated with the message, aligned with JSON

    private LocalDateTime createdAt; // Timestamp when the message was created

    // Getters and Setters
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

    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the user ID associated with the message.
     * @param userId the user's ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRentalId() {
        return rentalId;
    }

    /**
     * Sets the rental ID associated with the message.
     * @param rentalId the rental's ID
     */
    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp for the message.
     * @param createdAt the timestamp when the message was created
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
