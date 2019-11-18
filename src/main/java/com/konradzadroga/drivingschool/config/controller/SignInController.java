package com.konradzadroga.drivingschool.config.controller;

import com.konradzadroga.drivingschool.config.jwt.JwtProvider;
import com.konradzadroga.drivingschool.config.message.request.SignInDTO;
import com.konradzadroga.drivingschool.config.message.response.JwtTokenDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInController {

    private JwtProvider jwtProvider;
    private AuthenticationManager authenticationManager;

    public SignInController(JwtProvider jwtProvider, AuthenticationManager authenticationManager) {
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(value="signin", method= RequestMethod.POST)
    public ResponseEntity authenticateUser(@RequestBody SignInDTO signInDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInDTO.getUsername(),
                        signInDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtTokenDTO(token, userDetails.getUsername(), userDetails.getAuthorities()));
    }

}
