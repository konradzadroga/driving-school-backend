package com.konradzadroga.drivingschool.config.service;

import com.konradzadroga.drivingschool.rest_api.user.User;
import com.konradzadroga.drivingschool.rest_api.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: {}".format(s)));
        return MyUserDetails.build(user);
    }
}
