package com.example.rental_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RentalDTO {

    private Long id;
    private String name;
    private Integer surface;
    private Double price;
    private String picture;
    private String description;
    private UserDTO owner;
    private Long ownerId;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getSurface() {
        return surface;
    }

    public Double getPrice() {
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

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurface(Integer surface) {
        this.surface = surface;
    }

    public void setPrice(Double price) {
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

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}
