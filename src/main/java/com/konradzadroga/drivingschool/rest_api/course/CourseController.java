package com.konradzadroga.drivingschool.rest_api.course;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
}
