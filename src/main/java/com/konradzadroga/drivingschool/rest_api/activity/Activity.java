package com.konradzadroga.drivingschool.rest_api.activity;

import com.konradzadroga.drivingschool.rest_api.course.Course;
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
    private long id;

    @Column(name="date_of_activity")
    @DateTimeFormat
    private Date dateOfActivity;

    @Column
    private int rate;

    @ManyToOne
    private Course course;

    @ManyToOne
    private User instructor;

    @ManyToOne
    private User student;



}
