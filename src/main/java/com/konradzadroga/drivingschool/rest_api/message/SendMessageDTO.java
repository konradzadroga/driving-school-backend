package com.konradzadroga.drivingschool.rest_api.message;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SendMessageDTO {
    private String receiverUsername;
    private String content;
}
