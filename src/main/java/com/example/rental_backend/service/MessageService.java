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

    public MessageDTO sendMessage(String messageContent, Long user_id, Long rental_id) {
        logger.info("sendMessage called with message: {}, user_id: {}, rental_id: {}", messageContent, user_id, rental_id);
    
        // Vérifiez si user_id ou rental_id est null
        if (user_id == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        if (rental_id == null) {
            throw new IllegalArgumentException("Rental ID must not be null");
        }
    
        User user = userRepository.findById(user_id)
            .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + user_id));
        Rental rental = rentalRepository.findById(rental_id)
            .orElseThrow(() -> new IllegalArgumentException("Rental not found with ID: " + rental_id));
    
        Message message = new Message();
        message.setMessage(messageContent);
        message.setUser(user);
        message.setRental(rental);
        message.setCreatedAt(LocalDateTime.now());
    
        Message savedMessage = messageRepository.save(message);
    
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(savedMessage.getId());
        messageDTO.setMessage(savedMessage.getMessage());
        messageDTO.setUser_id(savedMessage.getUser().getId());
        messageDTO.setRental_id(savedMessage.getRental().getId());
        messageDTO.setCreatedAt(savedMessage.getCreatedAt());
    
        return messageDTO;
    }
    
}    
