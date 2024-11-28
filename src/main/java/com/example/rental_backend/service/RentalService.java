package com.example.rental_backend.service;

import com.cloudinary.*;
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

    public RentalService(RentalRepository rentalRepository, Cloudinary cloudinary) {
        this.rentalRepository = rentalRepository;
        this.cloudinary = cloudinary;
    }

    public List<RentalDTO> getAllRentals() {
        return rentalRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public Optional<RentalDTO> getRentalById(Long id) {
        return rentalRepository.findById(id).map(this::convertToDTO);
    }

     public RentalDTO createRental(RentalDTO rentalDTO, MultipartFile picture) throws IOException {
        // 1. Upload de l'image sur Cloudinary
        Map<String, Object> uploadResult = cloudinary.uploader().upload(picture.getBytes(), ObjectUtils.emptyMap());
        String pictureUrl = (String) uploadResult.get("url");

        // 2. Création de l'entité Rental
        Rental rental = new Rental();
        rental.setName(rentalDTO.getName());
        rental.setSurface(rentalDTO.getSurface());
        rental.setPrice(rentalDTO.getPrice());
        rental.setDescription(rentalDTO.getDescription());
        rental.setPicture(pictureUrl); // Stocke l'URL de l'image
        rental.setCreatedAt(LocalDateTime.now());
        rental.setUpdatedAt(LocalDateTime.now());

        rentalRepository.save(rental);

        // 3. Retourne un DTO
        rentalDTO.setId(rental.getId());
        rentalDTO.setPicture(pictureUrl);
        return rentalDTO;
    }
    

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
    
        // Utilise un constructeur pour créer UserDTO
        UserDTO ownerDTO = new UserDTO(
            rental.getOwner().getId(),
            rental.getOwner().getName(),
            rental.getOwner().getEmail(),
            rental.getOwner().getCreatedAt(),
            rental.getOwner().getUpdatedAt()
        );
        
        dto.setOwner(ownerDTO); // Set the converted owner
    
        return dto;
    }
    
}
