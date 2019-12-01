package com.konradzadroga.drivingschool.rest_api.message;

import com.konradzadroga.drivingschool.rest_api.user.GetUserInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMessageDTO {
    private Long id;
    private String content;
    private Date sentDate;
    private GetUserInfoDTO sender;
    private GetUserInfoDTO receiver;
}
