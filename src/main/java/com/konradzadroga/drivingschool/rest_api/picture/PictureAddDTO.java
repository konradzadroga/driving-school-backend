package com.konradzadroga.drivingschool.rest_api.picture;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class PictureAddDTO {
    @NotBlank
    private String hashCode;
}
