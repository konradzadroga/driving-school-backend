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

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(user ->
        {
            if (user.getUsername() != getCurrentUser().getUsername())
                users.add(user);
        });
        return users;
    }

    public User findUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
               new UsernameNotFoundException("User not found"));
        return user;
    }


    public User addCourseToUser(String username, int id) {
        User user = findUserByUsername(username);
        Course course = courseService.findCourseById(id);
        Set<Course> userCourses = user.getCourses();
        userCourses.add(course);
        user.setCourses(userCourses);
        userRepository.save(user);

        return user;
    }


    public List<GetUserInfoDTO> getUsersWithParticularRole(String name) {
        List<GetUserInfoDTO> users = new ArrayList<>();
        RoleName roleName = roleService.findRoleName(name);
        userRepository.findAllByRolesName(roleName).forEach(
                user -> {
                    if (user.getUsername() != getCurrentUser().getUsername())
                        users.add(GetUserInfoDTO.createDTO(user));
                });
        return users;
    }

    public User assignRoleToUser(String username, String name) {
        User user = findUserByUsername(username);
        RoleName roleName = roleService.findRoleName(name);
        Role role = roleService.findRoleByName(roleName);
        Set<Role> userRoles = user.getRoles();
        userRoles.add(role);
        user.setRoles(userRoles);
        userRepository.save(user);
        return user;
    }



}
