package com.example.rental_backend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import com.example.rental_backend.dto.RentalDTO;
import com.example.rental_backend.dto.UserDTO;
import com.example.rental_backend.model.Rental;
import com.example.rental_backend.repository.RentalRepository;

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

    // Constructor injection for dependencies
    public RentalService(RentalRepository rentalRepository, Cloudinary cloudinary) {
        this.rentalRepository = rentalRepository;
        this.cloudinary = cloudinary;
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
     * @param rentalDTO the data for the new rental
     * @param picture   the picture file to be uploaded
     * @return the created RentalDTO
     * @throws IOException if there is an error during file upload
     */
    public RentalDTO createRental(RentalDTO rentalDTO, MultipartFile picture) throws IOException {
        // Upload the picture to Cloudinary
        Map<String, Object> uploadResult = cloudinary.uploader().upload(picture.getBytes(), ObjectUtils.emptyMap());
        String pictureUrl = (String) uploadResult.get("url");

        // Map the DTO to a new Rental entity
        Rental rental = mapToEntity(rentalDTO);
        rental.setPicture(pictureUrl); // Set the picture URL
        rental.setCreatedAt(LocalDateTime.now());
        rental.setUpdatedAt(LocalDateTime.now());

        // Save the new rental entity to the database
        rentalRepository.save(rental);

        // Update the DTO with generated values and return it
        rentalDTO.setId(rental.getId());
        rentalDTO.setPicture(pictureUrl);
        return rentalDTO;
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

        // Map the owner entity to a UserDTO
        UserDTO ownerDTO = new UserDTO(
            rental.getOwner().getId(),
            rental.getOwner().getName(),
            rental.getOwner().getEmail(),
            rental.getOwner().getCreatedAt(),
            rental.getOwner().getUpdatedAt()
        );
        dto.setOwner(ownerDTO);

        return dto;
    }

    /**
     * Map a RentalDTO to a Rental entity.
     * 
     * @param rentalDTO the RentalDTO
     * @return the corresponding Rental entity
     */
    private Rental mapToEntity(RentalDTO rentalDTO) {
        Rental rental = new Rental();
        rental.setName(rentalDTO.getName());
        rental.setSurface(rentalDTO.getSurface());
        rental.setPrice(rentalDTO.getPrice());
        rental.setDescription(rentalDTO.getDescription());
        // Picture is not set here; it will be handled in the createRental method
        return rental;
    }
}
