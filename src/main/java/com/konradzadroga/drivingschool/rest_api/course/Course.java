package com.konradzadroga.drivingschool.rest_api.course;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name="courses")
public class Course {

    @Id
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

}
