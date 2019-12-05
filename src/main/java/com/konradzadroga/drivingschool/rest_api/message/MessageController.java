package com.konradzadroga.drivingschool.rest_api.message;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value="/messages/send", method = RequestMethod.POST)
    public ResponseEntity sendMessage(@RequestBody SendMessageDTO sentMessage) {
        messageService.sendMessage(sentMessage);
        return new ResponseEntity<>("Message has been sent", HttpStatus.OK);
    }

    @RequestMapping(value="/messages/{username}", method = RequestMethod.GET)
    public ResponseEntity<List<MessageDTO>> getMessagesWithParticularUser(@PathVariable String username) {
        List<MessageDTO> messages = messageService.getMessagesWithParticularUser(username);

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

}
