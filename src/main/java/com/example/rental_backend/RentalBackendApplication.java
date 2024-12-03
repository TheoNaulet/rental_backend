package com.example.rental_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for starting the Rental Backend application.
 * This class bootstraps the Spring Boot application.
 */
@SpringBootApplication // Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan
public class RentalBackendApplication {

    /**
     * The entry point of the Spring Boot application.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(RentalBackendApplication.class, args);
    }
}
