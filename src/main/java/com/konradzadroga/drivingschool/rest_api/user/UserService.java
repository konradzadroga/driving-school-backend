package com.konradzadroga.drivingschool.rest_api.user;
import com.konradzadroga.drivingschool.rest_api.course.Course;
import com.konradzadroga.drivingschool.rest_api.course.CourseService;
import com.konradzadroga.drivingschool.rest_api.role.Role;
import com.konradzadroga.drivingschool.rest_api.role.RoleName;
import com.konradzadroga.drivingschool.rest_api.role.RoleService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;
    private CourseService courseService;
    private RoleService roleService;

    public UserService(UserRepository userRepository, CourseService courseService, RoleService roleService) {
        this.userRepository = userRepository;
        this.courseService = courseService;
        this.roleService = roleService;
    }

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = findUserByUsername(username);
        return user;
    }

    public UserDTO getSignedInUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = findUserByUsername(username);
        UserDTO userDTO = UserDTO.createDTO(user);
        return userDTO;
    }


    public List<UserDTO> findAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        userRepository.findAll().forEach(user ->
        {
            if (user.getUsername() != getCurrentUser().getUsername())
                users.add(UserDTO.createDTO(user));
        });
        return users;
    }

    public User findUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
               new UsernameNotFoundException("User not found"));
        return user;
    }


    public UserDTO addCourseToUser(String username, int id) {
        User user = findUserByUsername(username);
        Course course = courseService.findCourseById(id);
        Set<Course> userCourses = user.getCourses();
        userCourses.add(course);
        user.setCourses(userCourses);
        UserDTO userDTO = UserDTO.createDTO(user);
        userRepository.save(user);
        return userDTO;
    }


    public List<UserBasicInfoDTO> getUsersWithParticularRole(String name) {
        List<UserBasicInfoDTO> users = new ArrayList<>();
        RoleName roleName = roleService.findRoleName(name);
        userRepository.findAllByRolesName(roleName).forEach(
                user -> {
                    if (user.getUsername() != getCurrentUser().getUsername())
                        users.add(UserBasicInfoDTO.createDTO(user));
                });
        return users;
    }

    public UserDTO assignRoleToUser(String username, String name) {
        User user = findUserByUsername(username);
        UserDTO userDTO;
        RoleName roleName = roleService.findRoleName(name);
        Role role = roleService.findRoleByName(roleName);
        Set<Role> userRoles = user.getRoles();
        userRoles.add(role);
        user.setRoles(userRoles);
        userRepository.save(user);
        userDTO = UserDTO.createDTO(user);
        return userDTO;
    }



}
