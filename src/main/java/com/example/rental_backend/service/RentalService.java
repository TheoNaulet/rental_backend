package com.example.rental_backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import com.example.rental_backend.dto.RentalDTO;
import com.example.rental_backend.model.Rental;
import com.example.rental_backend.model.User;
import com.example.rental_backend.repository.RentalRepository;
import com.example.rental_backend.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final Cloudinary cloudinary;
    private final UserRepository userRepository;

    // Constructor injection for dependencies
    public RentalService(RentalRepository rentalRepository, Cloudinary cloudinary, UserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.cloudinary = cloudinary;
        this.userRepository = userRepository;
    }

    /**
     * Retrieve all rentals from the database and convert them to DTOs.
     * 
     * @return a list of RentalDTO
     */
    public List<RentalDTO> getAllRentals() {
        return rentalRepository.findAll().stream()
            .map(this::convertToDTO) // Convert each Rental entity to a DTO
            .collect(Collectors.toList());
    }

    /**
     * Retrieve a specific rental by its ID.
     * 
     * @param id the ID of the rental
     * @return an Optional containing the RentalDTO if found, otherwise empty
     */
    public Optional<RentalDTO> getRentalById(Long id) {
        return rentalRepository.findById(id).map(this::convertToDTO);
    }

    /**
     * Create a new rental with an uploaded picture.
     *
     * @param name        the name of the rental
     * @param surface     the surface of the rental
     * @param price       the price of the rental
     * @param description the description of the rental
     * @param picture     the picture file to be uploaded
     * @return the created RentalDTO
     * @throws IOException if there is an error during file upload
     */
    public RentalDTO createRental(String name, Integer surface, Double price, String description, MultipartFile picture, String email) throws IOException {

        // Upload the picture to Cloudinary
        Map<String, Object> uploadResult = cloudinary.uploader().upload(picture.getBytes(), ObjectUtils.emptyMap());
        String pictureUrl = (String) uploadResult.get("url");
        
        // Find the owner by email
        User owner = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("Owner not found with ID: " + email));

        // Create the rental entity
        Rental rental = new Rental();
        rental.setName(name);
        rental.setSurface(surface);
        rental.setPrice(price);
        rental.setDescription(description);
        rental.setPicture(pictureUrl); // Set the uploaded picture URL
        rental.setCreatedAt(LocalDateTime.now());
        rental.setUpdatedAt(LocalDateTime.now());
        rental.setOwnerId(owner.getId());
        // rental.setOwner(owner); // Associe le propriétaire

        // Save the rental to the database
        rentalRepository.save(rental);

        // Map the saved entity to a DTO and return it
        return convertToDTO(rental);
    }

    /**
     * Update an existing rental by its ID.
     *
     * @param id          the ID of the rental to update
     * @param name        the updated name of the rental
     * @param surface     the updated surface of the rental
     * @param price       the updated price of the rental
     * @param description the updated description of the rental
     * @return the updated RentalDTO
     * @throws IllegalArgumentException if the rental is not found
     */
    public RentalDTO updateRental(Long id, String name, Integer surface, Double price, String description) {
        // Find the existing rental
        Rental rental = rentalRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Rental not found with ID: " + id));

        // Update the rental details
        rental.setName(name);
        rental.setSurface(surface);
        rental.setPrice(price);
        rental.setDescription(description);
        rental.setUpdatedAt(LocalDateTime.now());

        // Save the updated rental to the database
        rentalRepository.save(rental);

        // Convert the updated entity to DTO and return
        return convertToDTO(rental);
    }

    /**
     * Convert a Rental entity to a RentalDTO.
     * 
     * @param rental the Rental entity
     * @return the corresponding RentalDTO
     */
    private RentalDTO convertToDTO(Rental rental) {
        RentalDTO dto = new RentalDTO();
        dto.setId(rental.getId());
        dto.setName(rental.getName());
        dto.setSurface(rental.getSurface());
        dto.setPrice(rental.getPrice());
        dto.setPicture(rental.getPicture());
        dto.setDescription(rental.getDescription());
        dto.setCreatedAt(rental.getCreatedAt());
        dto.setUpdatedAt(rental.getUpdatedAt());
        dto.setOwnerId(rental.getOwnerId());
        return dto;
    }
}
