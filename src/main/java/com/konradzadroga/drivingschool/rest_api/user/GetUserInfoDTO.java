package com.konradzadroga.drivingschool.rest_api.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserInfoDTO {

    private String username;
    private String name;
    private String surname;
    private String email;

    public static GetUserInfoDTO createDTO(User user) {
        GetUserInfoDTO dto = new GetUserInfoDTO();

        dto.setUsername(user.getUsername());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setEmail(user.getEmail());

        return dto;
    }

}
