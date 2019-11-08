package com.konradzadroga.drivingschool.rest_api.user;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

}
