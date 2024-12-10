package com.example.rental_backend.service;

import com.example.rental_backend.dto.MessageDTO;
import com.example.rental_backend.model.Message;
import com.example.rental_backend.model.Rental;
import com.example.rental_backend.model.User;
import com.example.rental_backend.repository.MessageRepository;
import com.example.rental_backend.repository.RentalRepository;
import com.example.rental_backend.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Service for handling message-related operations, including sending messages
 * and ensuring proper validation of associated entities (user, rental).
 */
@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;

    /**
     * Constructor to inject the necessary repositories for message management.
     *
     * @param messageRepository Repository for accessing message data
     * @param userRepository Repository for accessing user data
     * @param rentalRepository Repository for accessing rental data
     */
    public MessageService(MessageRepository messageRepository, UserRepository userRepository, RentalRepository rentalRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
    }

    /**
     * Sends a message from a user regarding a specific rental.
     * 
     * @param messageContent The content of the message
     * @param userId The ID of the user sending the message
     * @param rentalId The ID of the rental associated with the message
     * @return A MessageDTO containing the details of the saved message
     * @throws IllegalArgumentException if userId or rentalId is null, or if the user/rental is not found
     */
    public MessageDTO sendMessage(String messageContent, Long userId, Long rentalId) {    
        // Validate the inputs
        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        if (rentalId == null) {
            throw new IllegalArgumentException("Rental ID must not be null");
        }
    
        // Retrieve the user and rental entities, or throw an exception if not found
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        Rental rental = rentalRepository.findById(rentalId)
            .orElseThrow(() -> new IllegalArgumentException("Rental not found with ID: " + rentalId));
    
        // Create a new message entity and populate it with the given data
        Message message = new Message();
        message.setMessage(messageContent);
        message.setUser(user);
        message.setRental(rental);
        message.setCreatedAt(LocalDateTime.now());
    
        // Save the message to the database
        Message savedMessage = messageRepository.save(message);
    
        // Convert the saved message entity into a DTO to return to the caller
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(savedMessage.getId());
        messageDTO.setMessage(savedMessage.getMessage());
        messageDTO.setUserId(savedMessage.getUser().getId());
        messageDTO.setRentalId(savedMessage.getRental().getId());
        messageDTO.setCreatedAt(savedMessage.getCreatedAt());
    
        return messageDTO;
    }
}
