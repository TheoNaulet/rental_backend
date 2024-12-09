package com.example.rental_backend.service;

import com.example.rental_backend.controller.MessageController;
import com.example.rental_backend.dto.MessageDTO;
import com.example.rental_backend.model.Message;
import com.example.rental_backend.model.Rental;
import com.example.rental_backend.model.User;
import com.example.rental_backend.repository.MessageRepository;
import com.example.rental_backend.repository.RentalRepository;
import com.example.rental_backend.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);


    public MessageService(MessageRepository messageRepository, UserRepository userRepository, RentalRepository rentalRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
    }

    public MessageDTO sendMessage(String messageContent, Long userId, Long rentalId) {
        logger.info("sendMessage called with message: {}, user_id: {}, rental_id: {}", messageContent, userId, rentalId);
    
        // Vérifiez si user_id ou rental_id est null
        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        if (rentalId == null) {
            throw new IllegalArgumentException("Rental ID must not be null");
        }
    
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        Rental rental = rentalRepository.findById(rentalId)
            .orElseThrow(() -> new IllegalArgumentException("Rental not found with ID: " + rentalId));
    
        Message message = new Message();
        message.setMessage(messageContent);
        message.setUser(user);
        message.setRental(rental);
        message.setCreatedAt(LocalDateTime.now());
    
        Message savedMessage = messageRepository.save(message);
    
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(savedMessage.getId());
        messageDTO.setMessage(savedMessage.getMessage());
        messageDTO.setUserId(savedMessage.getUser().getId());
        messageDTO.setRentalId(savedMessage.getRental().getId());
        messageDTO.setCreatedAt(savedMessage.getCreatedAt());
    
        return messageDTO;
    }
    
}    
