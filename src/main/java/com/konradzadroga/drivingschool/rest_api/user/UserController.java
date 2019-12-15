package com.konradzadroga.drivingschool.rest_api.user;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="users", method= RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        List<UserDTO> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostAuthorize("returnObject.getUsername() == authentication.principal.username")
    @RequestMapping(value="/users/me", method = RequestMethod.GET)
    public UserDTO getSignedInUser() {
        UserDTO user = userService.getSignedInUser();
        return user;
    }

    @RequestMapping(value="/users/courses/add/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserDTO> addCourseToUser(@PathVariable("id") int id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO user = userService.addCourseToUser(username, id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value="/users/roles/{role}", method = RequestMethod.GET)
    public ResponseEntity<List<UserBasicInfoDTO>> getUsersWithParticularRole(@PathVariable String role){
        List<UserBasicInfoDTO> users = userService.getUsersWithParticularRole(role);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/users/roles/assign/{role}/{username}", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> assignRoleToUser(@PathVariable String role, @PathVariable String username) {
        UserDTO user = userService.assignRoleToUser(username, role);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/users/{username}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<UserDTO> deleteUser(@PathVariable String username) {
        UserDTO user = userService.removeUser(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
