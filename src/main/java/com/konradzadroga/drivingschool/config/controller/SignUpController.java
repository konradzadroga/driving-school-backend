package com.konradzadroga.drivingschool.config.controller;

import com.konradzadroga.drivingschool.config.message.request.SignUpDTO;
import com.konradzadroga.drivingschool.config.message.response.ResponseMessage;
import com.konradzadroga.drivingschool.rest_api.role.Role;
import com.konradzadroga.drivingschool.rest_api.role.RoleName;
import com.konradzadroga.drivingschool.rest_api.role.RoleRepository;
import com.konradzadroga.drivingschool.rest_api.user.User;
import com.konradzadroga.drivingschool.rest_api.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class SignUpController {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public SignUpController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value="signup", method= RequestMethod.POST)
    public ResponseEntity<?> signUp(@RequestBody SignUpDTO signUpDTO) {
        if (userRepository.existsByUsername(signUpDTO.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Użytkownik o podanym loginie już istnieje."), HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(signUpDTO.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Użytkownik o podanym adresie e-mail już istnieje."), HttpStatus.BAD_REQUEST);
        }

        User user = new User(signUpDTO.getUsername(), signUpDTO.getName(), signUpDTO.getSurname(),
                signUpDTO.getEmail(), signUpDTO.getPesel(), signUpDTO.getBirthdate(), passwordEncoder.encode(signUpDTO.getPassword()));

        Set<Role> roles = new HashSet<>();

        signUpDTO.getRoles().forEach(role -> {
            switch(role) {
                case "ROLE_ADMIN":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Admin role not found"));
                    roles.add(adminRole);
                break;
                case "ROLE_USER":
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("User role not found"));
                    roles.add(userRole);
                    break;
                case "ROLE_INSTRUCTOR":
                    Role dbaRole = roleRepository.findByName(RoleName.ROLE_INSTRUCTOR)
                            .orElseThrow(() -> new RuntimeException("Instructor role not found"));
                    roles.add(dbaRole);
                    break;
            }
        });

        user.setRoles(roles);
        userRepository.save(user);

        return new ResponseEntity<>(new ResponseMessage("Rejestracja przebiegła pomyślnie"), HttpStatus.OK      );
    }
}
