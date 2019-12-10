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
    private String instructorNameAndSurname;

    public CourseDTO(Course course) {
        this.id = course.getId();
        this.category = course.getCategory();
        this.description = course.getDescription();
        this.places = course.getPlaces();
        this.startdate = course.getStartdate();
        this.cost = course.getCost();
        if (course.getInstructor() != null) {
            this.instructorUsername = course.getInstructor().getUsername();
            this.instructorNameAndSurname = course.getInstructor().getName() + " " + course.getInstructor().getSurname();
        } else {
            this.instructorUsername = "";
            this.instructorNameAndSurname = "";
        }
    }
}
