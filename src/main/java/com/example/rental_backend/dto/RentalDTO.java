package com.example.rental_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

/**
 * DTO for transferring rental information between the client and the backend.
 */
public class RentalDTO {

    private Long id; // Unique identifier for the rental

    private String name; // Name of the rental

    private Integer surface; // Surface area of the rental in square meters

    private Double price; // Price of the rental

    private String picture; // URL or path to the picture of the rental

    private String description; // Description of the rental

    @JsonProperty("owner_id")
    private Long ownerId; // ID of the owner (mapped to "owner_id" in JSON)

    @JsonProperty("created_at")
    private LocalDateTime createdAt; // Timestamp when the rental was created (mapped to "created_at" in JSON)

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt; // Timestamp when the rental was last updated (mapped to "updated_at" in JSON)

    // Getters
    /**
     * Gets the unique identifier for the rental.
     * @return the rental ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the name of the rental.
     * @return the rental name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the surface area of the rental in square meters.
     * @return the rental surface area
     */
    public Integer getSurface() {
        return surface;
    }

    /**
     * Gets the price of the rental.
     * @return the rental price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Gets the URL or path to the picture of the rental.
     * @return the rental picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Gets the description of the rental.
     * @return the rental description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the owner ID of the rental.
     * Mapped to "owner_id" in the JSON object.
     * @return the owner ID
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * Gets the timestamp when the rental was created.
     * Mapped to "created_at" in the JSON object.
     * @return the creation timestamp
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the timestamp when the rental was last updated.
     * Mapped to "updated_at" in the JSON object.
     * @return the update timestamp
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // Setters
    /**
     * Sets the unique identifier for the rental.
     * @param id the rental ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Sets the name of the rental.
     * @param name the rental name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the surface area of the rental in square meters.
     * @param surface the rental surface area
     */
    public void setSurface(Integer surface) {
        this.surface = surface;
    }

    /**
     * Sets the price of the rental.
     * @param price the rental price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Sets the URL or path to the picture of the rental.
     * @param picture the rental picture
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * Sets the description of the rental.
     * @param description the rental description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the owner ID of the rental.
     * Mapped to "owner_id" in the JSON object.
     * @param ownerId the owner ID
     */
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * Sets the timestamp when the rental was created.
     * Mapped to "created_at" in the JSON object.
     * @param createdAt the creation timestamp
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Sets the timestamp when the rental was last updated.
     * Mapped to "updated_at" in the JSON object.
     * @param updatedAt the update timestamp
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
