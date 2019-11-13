package com.konradzadroga.drivingschool.rest_api.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(user -> users.add(user));

        return users;
    }

    public User findUserByUsername(String username) {

        User user = userRepository.findByUsername(username).orElseThrow(() ->
               new UsernameNotFoundException("User not found"));

        return user;
    }

}
