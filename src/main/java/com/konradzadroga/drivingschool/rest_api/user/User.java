package com.konradzadroga.drivingschool.rest_api.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.konradzadroga.drivingschool.rest_api.activity.Activity;
import com.konradzadroga.drivingschool.rest_api.course.Course;
import com.konradzadroga.drivingschool.rest_api.message.Message;
import com.konradzadroga.drivingschool.rest_api.role.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max=40)
    private String username;

    @NotBlank
    @Size(max=30)
    private String name;

    @NotBlank
    @Size(max=35)
    private String surname;

    @Email
    @Size(max=60)
    private String email;

    @NotBlank
    @Size(min=11, max=11)
    private String pesel;

    @DateTimeFormat
    private Date birthdate;

    @NotBlank
    @Size(min=6)
    @JsonIgnore
    private String password;

    @ManyToMany
    private Set<Role> roles;

    @ManyToMany
    private Set<Course> courses;

    @OneToMany(mappedBy = "student")
    private Collection<Activity> activities;

    @JsonIgnore
    @OneToMany(mappedBy = "receiver")
    private Collection<Message> receivedMessages;

    @JsonIgnore
    @OneToMany(mappedBy = "sender")
    private Collection<Message> sentMessages;

    public User(@NotBlank @Size(max = 40) String username, @NotBlank @Size(max = 30) String name, @NotBlank @Size(max = 35) String surname, @Email @Size(max = 60) String email, @NotBlank @Size(min = 16, max = 16) String pesel, @NotBlank Date birthdate, @NotBlank @Size(min = 6) String password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.pesel = pesel;
        this.birthdate = birthdate;
        this.password = password;
    }
}
