package com.example.rental_backend.config;

import com.cloudinary.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for integrating the Cloudinary service with the application.
 * This class defines a Spring Bean for Cloudinary, configured via environment variables.
 */
@Configuration
public class CloudinaryConfig {

    /**
     * Creates and provides a Cloudinary instance as a Spring Bean.
     * The configuration is based on the `CLOUDINARY_URL` environment variable.
     *
     * @return a configured Cloudinary instance
     * @throws IllegalStateException if the `CLOUDINARY_URL` is missing or invalid
     */
    @Bean
    public Cloudinary cloudinary() {
        try {
            // Load environment variables using Dotenv
            Dotenv dotenv = Dotenv.load();

            // Retrieve the CLOUDINARY_URL from the environment
            String cloudinaryUrl = dotenv.get("CLOUDINARY_URL");

            // Validate the presence of the CLOUDINARY_URL variable
            if (cloudinaryUrl == null || cloudinaryUrl.isEmpty()) {
                throw new IllegalStateException("CLOUDINARY_URL environment variable is missing");
            }

            // Create and return a Cloudinary instance using the retrieved URL
            return new Cloudinary(cloudinaryUrl);
        } catch (Exception e) {
            // Handle any exceptions during configuration
            throw new IllegalStateException("Failed to configure Cloudinary", e);
        }
    }
}
