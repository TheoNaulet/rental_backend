package com.example.rental_backend.config;

import com.cloudinary.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        try {
            Dotenv dotenv = Dotenv.load();

            String cloudinaryUrl = dotenv.get("CLOUDINARY_URL");

            return new Cloudinary(cloudinaryUrl);

        } catch (Exception e) {
            throw new IllegalStateException("Failed to configure Cloudinary", e);
        }
    }
}
