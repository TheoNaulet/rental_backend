package com.example.rental_backend.controller;

import com.example.rental_backend.dto.MessageDTO;
import com.example.rental_backend.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

        private static final Logger logger = LoggerFactory.getLogger(MessageController.class);


    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

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
            logger.info("MessageDTO: " + messageDTO.toString());  
            MessageDTO sentMessage = messageService.sendMessage(
                messageDTO.getMessage(),
                messageDTO.getUser_id(),
                messageDTO.getRental_id()
            );

            logger.info("SENT MESSAGE: " + sentMessage);  


            return ResponseEntity.ok(sentMessage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
