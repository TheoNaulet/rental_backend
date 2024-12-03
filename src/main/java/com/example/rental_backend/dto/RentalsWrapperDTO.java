package com.example.rental_backend.dto;

import java.util.List;

/**
 * DTO for wrapping a list of rentals in an API response.
 * This ensures a consistent structure for the API's output.
 */
public class RentalsWrapperDTO {

    private List<RentalDTO> rentals; // List of rental data transfer objects

    /**
     * Constructor for initializing the RentalsWrapperDTO.
     * @param rentals the list of RentalDTO objects to wrap
     */
    public RentalsWrapperDTO(List<RentalDTO> rentals) {
        this.rentals = rentals;
    }

    /**
     * Gets the list of RentalDTO objects.
     * @return the list of rentals
     */
    public List<RentalDTO> getRentals() {
        return rentals;
    }

    /**
     * Sets the list of RentalDTO objects.
     * @param rentals the new list of rentals
     */
    public void setRentals(List<RentalDTO> rentals) {
        this.rentals = rentals;
    }
}
