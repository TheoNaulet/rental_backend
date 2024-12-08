package com.example.rental_backend.controller;

import com.example.rental_backend.dto.RentalDTO;
import com.example.rental_backend.dto.RentalsWrapperDTO;
import com.example.rental_backend.dto.ResponseMessageDTO;
import com.example.rental_backend.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    /**
     * Constructor injection for dependencies.
     * 
     * @param rentalService the service managing rental operations
     * @param userRepository the repository for user data
     */
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
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
     * Endpoint to create a new rental with an uploaded picture.
     *
     * This method handles the creation of a rental by accepting details such as the name,
     * surface, price, description, and a picture file. It retrieves the authenticated user's
     * email to associate the rental with the correct user. The rental is then created and
     * stored in the database.
     *
     * @param name        the name of the rental to be created
     * @param surface     the surface area of the rental (in square meters)
     * @param price       the price of the rental (in the chosen currency)
     * @param description a brief description of the rental
     * @param picture     the picture file representing the rental
     * @param authentication the authentication object containing user credentials
     * @return a ResponseEntity containing a success message or an error message
     */
    @Operation(summary = "Create a new rental")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rental created!"),
        @ApiResponse(responseCode = "400", description = "Invalid input data provided"),
        @ApiResponse(responseCode = "500", description = "Internal server error occurred")
    })
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ResponseMessageDTO> createRental(
        @RequestParam("name") String name,
        @RequestParam("surface") Integer surface,
        @RequestParam("price") Double price,
        @RequestParam("description") String description,
        @RequestParam("picture") MultipartFile picture,
        Authentication authentication
    ) {
        try {
            // Delegate rental creation to the service layer
            rentalService.createRental(name, surface, price, description, picture, authentication.getName());

            // Return a success message upon successful creation
            return ResponseEntity.ok(new ResponseMessageDTO("Rental created !"));
        } catch (IllegalArgumentException e) {
            // Handle invalid input errors
            return ResponseEntity.badRequest().body(new ResponseMessageDTO(e.getMessage()));
        } catch (Exception e) {
            // Handle unexpected server errors
            return ResponseEntity.status(500).body(new ResponseMessageDTO("An internal error occurred"));
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
    public ResponseEntity<ResponseMessageDTO> updateRental(
        @PathVariable Long id,
        @RequestParam("name") String name,
        @RequestParam("surface") Integer surface,
        @RequestParam("price") Double price,
        @RequestParam("description") String description
    ) {
        try {
            // Update the rental entity via the service
                rentalService.updateRental(id, name, surface, price, description);

            // Return the updated rental
            return ResponseEntity.ok(new ResponseMessageDTO("Rental updated !"));
        } catch (IllegalArgumentException e) {
            // Handle invalid input errors
            return ResponseEntity.badRequest().body(new ResponseMessageDTO(e.getMessage()));
        } catch (Exception e) {
            // Handle unexpected server errors
            return ResponseEntity.status(500).body(new ResponseMessageDTO("An internal error occurred"));
        }
    }

}
