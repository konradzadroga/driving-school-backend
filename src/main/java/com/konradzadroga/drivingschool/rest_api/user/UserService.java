package com.konradzadroga.drivingschool.rest_api.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
