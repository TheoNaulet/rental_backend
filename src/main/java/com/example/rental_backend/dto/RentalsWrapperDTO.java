package com.example.rental_backend.dto;

import java.util.List;

/**
 * DTO for wrapping the list of rentals in an API response.
 */
public class RentalsWrapperDTO {

    private List<RentalDTO> rentals; // Encapsulated list of rentals

    // Constructor
    public RentalsWrapperDTO(List<RentalDTO> rentals) {
        this.rentals = rentals;
    }

    // Getter
    public List<RentalDTO> getRentals() {
        return rentals;
    }

    // Setter
    public void setRentals(List<RentalDTO> rentals) {
        this.rentals = rentals;
    }
}
