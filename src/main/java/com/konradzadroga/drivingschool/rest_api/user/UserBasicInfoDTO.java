package com.konradzadroga.drivingschool.rest_api.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicInfoDTO {

    private String username;
    private String name;
    private String surname;
    private String email;


    public UserBasicInfoDTO(User user) {
        this.username = user.getUsername();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
    }


}
