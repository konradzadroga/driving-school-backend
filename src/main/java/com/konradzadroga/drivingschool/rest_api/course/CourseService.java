package com.konradzadroga.drivingschool.rest_api.course;

import org.springframework.stereotype.Service;
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

    public Course findCourseById(int id) {
        Course course = courseRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Course not found"));

        return course;
    }

}
