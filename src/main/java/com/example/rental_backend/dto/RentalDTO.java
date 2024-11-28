package com.example.rental_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RentalDTO {

    private Long id;
    private String name;
    private BigDecimal surface;
    private BigDecimal price;
    private String picture;
    private String description;
    private UserDTO owner;
    private Long ownerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSurface() {
        return surface;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getPicture() {
        return picture;
    }

    public String getDescription() {
        return description;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurface(BigDecimal surface) {
        this.surface = surface;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
