package com.konradzadroga.drivingschool.rest_api.message;
import com.konradzadroga.drivingschool.rest_api.user.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Entity
@Table(name="messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String content;

    @Column
    private Date sentDate;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

}
