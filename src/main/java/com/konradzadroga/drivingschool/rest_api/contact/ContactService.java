package com.konradzadroga.drivingschool.rest_api.contact;

import com.konradzadroga.drivingschool.config.service.MailService;
import com.konradzadroga.drivingschool.rest_api.user.User;
import com.konradzadroga.drivingschool.rest_api.user.UserDTO;
import com.konradzadroga.drivingschool.rest_api.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private UserService userService;
    private MailService mailService;

    public ContactService(UserService userService, MailService mailService) {
        this.mailService = mailService;
        this.userService = userService;
    }

    public ContactDTO sendContactMessage(ContactDTO contactDTO) {
        UserDTO user = userService.getSignedInUser();
        mailService.sendContactMessage(user, contactDTO.getContent());
        return contactDTO;
    }

}
