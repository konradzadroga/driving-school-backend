package com.konradzadroga.drivingschool.rest_api.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCourseDTO {
    private String category;
    private String description;
    private int places;
    private Date startdate;
    private float cost;
    private String instructorUsername;
}
