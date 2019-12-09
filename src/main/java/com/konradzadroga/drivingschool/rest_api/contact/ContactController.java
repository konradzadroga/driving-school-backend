package com.konradzadroga.drivingschool.rest_api.contact;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(method = RequestMethod.POST, value="contact")
    public ResponseEntity<ContactDTO> sendContactRequest(@RequestBody ContactDTO contactDTO) {
        contactService.sendContactMessage(contactDTO);
        return new ResponseEntity<>(contactDTO, HttpStatus.OK);
    }

}
