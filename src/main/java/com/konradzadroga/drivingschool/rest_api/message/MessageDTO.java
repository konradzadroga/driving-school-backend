package com.konradzadroga.drivingschool.rest_api.message;

import com.konradzadroga.drivingschool.rest_api.user.UserBasicInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private Long id;
    private String content;
    private Date sentDate;
    private UserBasicInfoDTO sender;
    private UserBasicInfoDTO receiver;
}
