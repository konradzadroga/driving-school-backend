package com.konradzadroga.drivingschool.config.message.request;


import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class SignUpDTO {
    private String username;
    private String name;
    private String surname;
    private String email;
    private String pesel;
    private Date birthdate;
    private String password;
    private Set<String> roles;
}
