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

    public static UserBasicInfoDTO createDTO(User user) {
        UserBasicInfoDTO dto = new UserBasicInfoDTO();

        dto.setUsername(user.getUsername());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setEmail(user.getEmail());

        return dto;
    }

}
