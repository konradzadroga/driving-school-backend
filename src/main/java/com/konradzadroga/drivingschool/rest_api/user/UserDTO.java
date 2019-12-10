package com.konradzadroga.drivingschool.rest_api.user;

import com.konradzadroga.drivingschool.rest_api.course.Course;
import com.konradzadroga.drivingschool.rest_api.course.CourseDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
public class UserDTO {

    private String username;
    private String name;
    private String surname;
    private String email;
    private String pesel;
    private Date birthdate;
    private Set<String> roles;
    private List<CourseDTO> courses;

    public UserDTO(User user) {
        Set<String> userRoles = new HashSet<>();
        user.getRoles().forEach(role -> {
            userRoles.add(role.getName().name());
        });
        List<CourseDTO> courseDTOs = new ArrayList<>();
        user.getCourses().forEach(course -> {
                courseDTOs.add(new CourseDTO(course));
        });
        this.username = user.getUsername();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.pesel = user.getPesel();
        this.birthdate = user.getBirthdate();
        this.roles = userRoles;
        this.courses = courseDTOs;

    }


}
