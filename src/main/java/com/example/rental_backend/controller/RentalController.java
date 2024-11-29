package com.example.rental_backend.controller;

import com.example.rental_backend.dto.RentalDTO;
import com.example.rental_backend.dto.RentalsWrapperDTO;
import com.example.rental_backend.model.User;
import com.example.rental_backend.repository.UserRepository;
import com.example.rental_backend.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.HashMap;

import java.util.Map;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final UserRepository userRepository;
    private final RentalService rentalService;

    /**
     * Constructor injection for dependencies.
     * 
     * @param rentalService the service managing rental operations
     * @param userRepository the repository for user data
     */
    public RentalController(RentalService rentalService, UserRepository userRepository) {
        this.rentalService = rentalService;
        this.userRepository = userRepository;
    }
    
    @Operation(summary = "Get all rentals")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved rentals"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<RentalsWrapperDTO> getAllRentals() {
        try {
            // Retrieve the list of rentals from the service
            List<RentalDTO> rentals = rentalService.getAllRentals();

            // Wrap the rentals list in RentalsWrapperDTO
            RentalsWrapperDTO response = new RentalsWrapperDTO(rentals);

            // Return the wrapped response
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }


    /**
     * Retrieve a rental by its ID.
     * 
     * @param id the ID of the rental
     * @return a ResponseEntity containing the RentalDTO if found, or 404 Not Found
     */
    @Operation(summary = "Get rental by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rental found"),
        @ApiResponse(responseCode = "404", description = "Rental not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RentalDTO> getRentalById(@PathVariable Long id) {
        Optional<RentalDTO> rental = rentalService.getRentalById(id);
        return rental.map(ResponseEntity::ok) // Return 200 OK with the rental data
            .orElseGet(() -> ResponseEntity.notFound().build()); // Return 404 if not found
    }

    /**
     * Create a new rental with an uploaded picture.
     *
     * @param name        the name of the rental
     * @param surface     the surface of the rental
     * @param price       the price of the rental
     * @param description the description of the rental
     * @param picture     the picture file to be uploaded
     * @return a ResponseEntity containing the created RentalDTO or an error status
     */
    @Operation(summary = "Create a new rental")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rental successfully created"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<RentalDTO> createRental(
        @RequestParam("name") String name,
        @RequestParam("surface") Integer surface,
        @RequestParam("price") Double price,
        @RequestParam("description") String description,
        @RequestParam("picture") MultipartFile picture,
        Authentication authentication
    ) {
        try {
            String email = authentication.getName();

            // Rechercher l'utilisateur dans la base de données
            User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

            // Create the rental entity via the service
            RentalDTO createdRental = rentalService.createRental(name, surface, price, description, picture, user.getId());


            // Return the created rental
            return ResponseEntity.ok(createdRental);
        } catch (IOException e) {
            // Handle file upload exceptions
            return ResponseEntity.status(500).body(null);
        }
    }

    /**
     * Update an existing rental by its ID.
     *
     * @param id          the ID of the rental to update
     * @param name        the updated name of the rental
     * @param surface     the updated surface of the rental
     * @param price       the updated price of the rental
     * @param description the updated description of the rental
     * @return a ResponseEntity containing the updated RentalDTO or an error status
     */
    @Operation(summary = "Update an existing rental")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rental successfully updated"),
        @ApiResponse(responseCode = "404", description = "Rental not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RentalDTO> updateRental(
        @PathVariable Long id,
        @RequestParam("name") String name,
        @RequestParam("surface") Integer surface,
        @RequestParam("price") Double price,
        @RequestParam("description") String description
    ) {
        try {
            // Update the rental entity via the service
            RentalDTO updatedRental = rentalService.updateRental(id, name, surface, price, description);

            // Return the updated rental
            return ResponseEntity.ok(updatedRental);
        } catch (IllegalArgumentException e) {
            // Handle not found exception
            return ResponseEntity.status(404).body(null);
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(500).body(null);
        }
    }

}
