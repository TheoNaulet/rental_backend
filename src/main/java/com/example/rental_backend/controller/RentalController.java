package com.example.rental_backend.controller;

import com.example.rental_backend.dto.RentalDTO;
import com.example.rental_backend.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    /**
     * Constructor injection for RentalService dependency.
     * 
     * @param rentalService the service managing rental operations
     */
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    /**
     * Retrieve all rentals.
     * 
     * @return a list of RentalDTO objects
     */
    @Operation(summary = "Get all rentals")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved rentals"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<RentalDTO> getAllRentals() {
        return rentalService.getAllRentals();
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
        @RequestParam("picture") MultipartFile picture
    ) {
        try {
            // Create the rental entity via the service
            RentalDTO createdRental = rentalService.createRental(name, surface, price, description, picture, 3L);

            // Return the created rental
            return ResponseEntity.ok(createdRental);
        } catch (IOException e) {
            // Handle file upload exceptions
            return ResponseEntity.status(500).body(null);
        }
    }
}
