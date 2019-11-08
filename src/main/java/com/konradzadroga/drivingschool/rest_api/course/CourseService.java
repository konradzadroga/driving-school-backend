package com.konradzadroga.drivingschool.rest_api.course;

import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
}
