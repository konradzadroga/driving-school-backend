package com.konradzadroga.drivingschool.rest_api.course;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> findAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses;
    }

}
