package com.konradzadroga.drivingschool.rest_api.course;

import com.konradzadroga.drivingschool.rest_api.user.UserBasicInfoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Set;

@RestController
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public ResponseEntity<List<CourseDTO>> findAllCourses() {
        List<CourseDTO> courses = courseService.findAllCourses();

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @RequestMapping(value = "/courses/instructor/{username}", method = RequestMethod.GET)
    public ResponseEntity<List<CourseDTO>> findInstructorCourses(@PathVariable  String username) {
        List<CourseDTO> courses = courseService.findInstructorCourses(username);

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

}
