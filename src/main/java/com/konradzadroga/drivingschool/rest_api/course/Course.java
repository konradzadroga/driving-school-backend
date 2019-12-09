package com.konradzadroga.drivingschool.rest_api.course;

import com.konradzadroga.drivingschool.rest_api.user.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String category;

    @NotBlank
    private String description;

    @Column
    private Date startdate;

    @Column
    private int places;

    @Column
    private float cost;

    @ManyToOne
    private User instructor;



}
