package com.example.rental_backend.config;

import com.cloudinary.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    /**
     * Configures and provides a Cloudinary instance as a Spring Bean.
     * 
     * @return a configured Cloudinary instance
     * @throws IllegalStateException if configuration fails
     */
    @Bean

    public Cloudinary cloudinary() {
        try {
            // Load environment variables using Dotenv
            Dotenv dotenv = Dotenv.load();

            // Retrieve the CLOUDINARY_URL from the environment
            String cloudinaryUrl = dotenv.get("CLOUDINARY_URL");

            // Ensure the URL is not null or empty
            if (cloudinaryUrl == null || cloudinaryUrl.isEmpty()) {
                throw new IllegalStateException("CLOUDINARY_URL environment variable is missing");
            }

            // Create and return a Cloudinary instance
            return new Cloudinary(cloudinaryUrl);
        } catch (Exception e) {
            // Wrap any exception into an IllegalStateException for better error handling
            throw new IllegalStateException("Failed to configure Cloudinary", e);
        }
    }
}
