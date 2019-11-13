package com.konradzadroga.drivingschool.rest_api.course;

import com.konradzadroga.drivingschool.rest_api.user.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name="courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;


}
