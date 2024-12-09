package com.example.rental_backend.controller;

import com.example.rental_backend.dto.MessageDTO;
import com.example.rental_backend.dto.ResponseMessageDTO;
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
        @ApiResponse(responseCode = "200", description = "Message send with success"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<ResponseMessageDTO> sendMessage(@RequestBody MessageDTO messageDTO) {
        try {
            messageService.sendMessage(
                messageDTO.getMessage(),
                messageDTO.getUserId(),
                messageDTO.getRentalId()
            );
            return ResponseEntity.ok(new ResponseMessageDTO("Message send with success"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ResponseMessageDTO(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ResponseMessageDTO("An internal error occurred"));
        }
    }
}
