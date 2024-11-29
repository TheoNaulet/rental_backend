package com.example.rental_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "RENTALS")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer surface;
    private Double price;
    private String picture;

    @Column(length = 2000)
    private String description;

    @Column(name = "owner_id", nullable = false)
    private Long owner_id;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "updated_at")
    private LocalDateTime updated_at;

// Getters and Setters

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public Integer getSurface() {
    return surface;
}

public void setSurface(Integer surface) {
    this.surface = surface;
}

public Double getPrice() {
    return price;
}

public void setPrice(Double price) {
    this.price = price;
}

public String getPicture() {
    return picture;
}

public void setPicture(String picture) {
    this.picture = picture;
}

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

public Long getOwner_id() {
    return owner_id;
}

public void setOwner_id(Long owner_id) {
    this.owner_id = owner_id;
}


public LocalDateTime getCreated_at() {
    return created_at;
}

public void setCreated_at(LocalDateTime created_at) {
    this.created_at = created_at;
}

public LocalDateTime getUpdated_at() {
    return updated_at;
}

public void setUpdated_at(LocalDateTime updated_at) {
    this.updated_at = updated_at;
}
}
