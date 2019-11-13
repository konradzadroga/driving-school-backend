package com.konradzadroga.drivingschool.rest_api.course;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ResponseEntity<List<Course>> findAllCourses() {
        List<Course> courses = courseService.findAllCourses();

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

}
