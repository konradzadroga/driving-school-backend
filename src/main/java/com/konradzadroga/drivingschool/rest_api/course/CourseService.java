package com.konradzadroga.drivingschool.rest_api.course;
import com.konradzadroga.drivingschool.rest_api.user.User;
import com.konradzadroga.drivingschool.rest_api.user.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    private UserService userService;

    public CourseService(CourseRepository courseRepository, @Lazy UserService userService) {
        this.courseRepository = courseRepository;
        this.userService = userService;
    }


    public List<CourseDTO> findAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDTO> courseDTOs = new ArrayList<>();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        courses.forEach(course -> {
            System.out.println(course.getInstructor().getUsername());
            if (!course.getInstructor().getUsername().equals(username)) {
                courseDTOs.add(new CourseDTO(course));
            }
        });
        return courseDTOs;
    }

    public Course findCourseById(int id) {
        Course course = courseRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Course not found"));
        return course;
    }

    public CourseDTO findCourseDTOById(int id) {
        Course course = findCourseById(id);
        CourseDTO courseDTO = new CourseDTO(course);

        return courseDTO;
    }

    public void updateCoursePlaces(int id) {
        Course course = findCourseById(id);
        int places = course.getPlaces();
        places = places - 1;
        course.setPlaces(places);
        courseRepository.save(course);
    }

    public List<CourseDTO> findInstructorCourses(String username) {
        List<CourseDTO> instructorCourses = new ArrayList<>();
        List<Course> courses = courseRepository.findAllByInstructorUsername(username);
        courses.forEach(course -> {
            instructorCourses.add(new CourseDTO(course));
        });

        return instructorCourses;
    }

    public CourseDTO addCourse(AddCourseDTO courseDTO) {
        User instructor = userService.findUserByUsername(courseDTO.getInstructorUsername());
        Course course = new Course(courseDTO, instructor);
        courseRepository.save(course);
        CourseDTO returnedCourse = new CourseDTO(course);

        return returnedCourse;
    }



}
