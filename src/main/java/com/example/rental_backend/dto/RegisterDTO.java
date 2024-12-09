// DTO for user registration
package com.example.rental_backend.dto;

/**
 * Data Transfer Object for user registration.
 */
public class RegisterDTO {

    private String name; // User's name
    private String email; // User's email address
    private String password; // User's password (plain text, to be hashed later)

    /**
     * Gets the user's name.
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     * @param name the user's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user's email address.
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     * @param email a valid email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's password.
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     * This should be hashed before being stored in the database.
     * @param password the plain text password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
