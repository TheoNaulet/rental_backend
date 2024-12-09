package com.example.rental_backend.dto;

public class ResponseMessageDTO {
    private String message; // Message to be sent in the response

    /**
     * Constructor for ResponseMessageDTO.
     * @param message the message to be sent in the response
     */
    public ResponseMessageDTO(String message) {
        this.message = message;
    }

    /**
     * Gets the message to be sent in the response.
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message to be sent in the response.
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
