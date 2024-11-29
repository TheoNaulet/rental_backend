package com.example.rental_backend.controller;

import com.example.rental_backend.dto.MessageDTO;
import com.example.rental_backend.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling message-related operations.
 */
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    /**
     * Constructor for dependency injection.
     *
     * @param messageService the service handling message operations
     */
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * Endpoint to send a message.
     *
     * @param messageDTO the details of the message to be sent
     * @return a ResponseEntity containing the sent message or an error status
     */
    @Operation(summary = "Send a message")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Message successfully sent"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<MessageDTO> sendMessage(
        @RequestBody MessageDTO messageDTO
    ) {
        try {
            // Process the message
            MessageDTO sentMessage = messageService.sendMessage(
                messageDTO.getMessage(),
                messageDTO.getUser_id(),
                messageDTO.getRental_id()
            );

            // Return the response
            return ResponseEntity.ok(sentMessage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
