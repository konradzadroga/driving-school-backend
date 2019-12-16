package com.konradzadroga.drivingschool.rest_api.course;

import com.konradzadroga.drivingschool.rest_api.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@NoArgsConstructor
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

    public Course(AddCourseDTO courseDTO, User instructor) {
        this.category = courseDTO.getCategory();
        this.description = courseDTO.getDescription();
        this.startdate = courseDTO.getStartdate();
        this.places = courseDTO.getPlaces();
        this.cost = courseDTO.getCost();
        this.instructor = instructor;
    }



}
