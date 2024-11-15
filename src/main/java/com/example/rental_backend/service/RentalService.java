package com.example.rental_backend.service;

import com.example.rental_backend.dto.RentalDTO;
import com.example.rental_backend.dto.UserDTO;
import com.example.rental_backend.model.Rental;
import com.example.rental_backend.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<RentalDTO> getAllRentals() {
        return rentalRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public Optional<RentalDTO> getRentalById(Long id) {
        return rentalRepository.findById(id).map(this::convertToDTO);
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

        // Convert Owner to UserDTO
        UserDTO ownerDTO = new UserDTO();
        ownerDTO.setId(rental.getOwner().getId());
        ownerDTO.setEmail(rental.getOwner().getEmail());
        ownerDTO.setName(rental.getOwner().getName());
        
        dto.setOwner(ownerDTO); // Set the converted owner

        return dto;
    }
}
