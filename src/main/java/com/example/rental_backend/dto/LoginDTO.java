package com.example.rental_backend.dto;

/**
 * DTO for holding user login information.
 */
public class LoginDTO {
    private String email; // User's email address
    private String password; // User's password

    // Getters and setters
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

    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     * @param password the plain-text password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the email using a user-provided login identifier.
     * This can be useful if the login is interpreted as an email.
     * @param login the user's login identifier
     */
    public void setLogin(String login) {
        this.email = login; 
    }
}
