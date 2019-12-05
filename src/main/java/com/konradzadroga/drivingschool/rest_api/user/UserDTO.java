package com.konradzadroga.drivingschool.rest_api.user;

import com.konradzadroga.drivingschool.rest_api.course.Course;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private Set<Course> courses;

    public static UserDTO createDTO(User user) {
        UserDTO dto = new UserDTO();
        Set<String> userRoles = new HashSet<>();
        user.getRoles().forEach(role -> {
            userRoles.add(role.getName().name());
        });
        dto.setUsername(user.getUsername());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setEmail(user.getEmail());
        dto.setPesel(user.getPesel());
        dto.setBirthdate(user.getBirthdate());
        dto.setRoles(userRoles);
        dto.setCourses(user.getCourses());
        return dto;
    }

}
