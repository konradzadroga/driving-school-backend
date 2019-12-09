package com.konradzadroga.drivingschool.rest_api.course;

import com.konradzadroga.drivingschool.rest_api.user.User;
import com.konradzadroga.drivingschool.rest_api.user.UserBasicInfoDTO;
import com.konradzadroga.drivingschool.rest_api.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private int id;
    private String category;
    private String description;
    private int places;
    private Date startdate;
    private float cost;
    private String instructorUsername;

    public CourseDTO(Course course, String instructorUsername) {
        this.id = course.getId();
        this.category = course.getCategory();
        this.description = course.getDescription();
        this.places = course.getPlaces();
        this.startdate = course.getStartdate();
        this.cost = course.getCost();
        this.instructorUsername = instructorUsername;
    }
}
