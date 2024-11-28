package com.example.rental_backend.controller;

import com.example.rental_backend.dto.RentalDTO;
import com.example.rental_backend.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.ResponseEntity;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @Operation(summary = "Get all rentals")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved rentals"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    @GetMapping
    public List<RentalDTO> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalDTO> getRentalById(@PathVariable Long id) {
        Optional<RentalDTO> rental = rentalService.getRentalById(id);
        return rental.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<RentalDTO> createRental(
        @RequestPart("rental") RentalDTO rentalDTO,
        @RequestPart("picture") MultipartFile picture
    ) {
        try {
            RentalDTO createdRental = rentalService.createRental(rentalDTO, picture);
            return ResponseEntity.ok(createdRental);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
