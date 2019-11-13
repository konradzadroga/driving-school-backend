package com.konradzadroga.drivingschool.rest_api.activity;

import com.konradzadroga.drivingschool.rest_api.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "activities")
@Data
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat
    private Date date_of_activity;

    @ManyToOne
    private User student;



}
