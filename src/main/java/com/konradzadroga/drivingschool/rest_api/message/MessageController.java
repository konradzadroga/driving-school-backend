package com.konradzadroga.drivingschool.rest_api.message;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/messages/send", method = RequestMethod.POST)
    public ResponseEntity<Message> sendMessage(@RequestBody SendMessageDTO sentMessage) {
        Message message = messageService.sendMessage(sentMessage);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/messages/{username}", method = RequestMethod.GET)
    public ResponseEntity<List<GetMessageDTO>> getMessagesWithParticularUser(@PathVariable String username) {
        List<GetMessageDTO> messages = messageService.getMessagesWithParticularUser(username);

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

}
