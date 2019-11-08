package com.konradzadroga.drivingschool.config.message.response;

import lombok.Data;

@Data
public class JwtTokenDTO {
    private String token;
    private String type = "Bearer";

    public JwtTokenDTO(String token) {
        this.token = token;
    }


}
