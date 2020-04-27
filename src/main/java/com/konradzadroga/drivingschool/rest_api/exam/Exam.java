package com.konradzadroga.drivingschool.rest_api.exam;

import com.konradzadroga.drivingschool.rest_api.course.Course;
import com.konradzadroga.drivingschool.rest_api.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Data
@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="date_of_exam")
    @DateTimeFormat
    private Date dateOfExam;

    @ManyToOne
    private User student;

    @ManyToOne
    private User instructor;

    @ManyToOne
    private Course course;

    @Column
    private Boolean occupied;

    @Column
    private Boolean passed;

    public Exam(Date dateOfExam, Course course, User instructor) {
        this.dateOfExam = dateOfExam;
        this.student = null;
        this.course = course;
        this.instructor = instructor;
        this.occupied = false;
        this.passed = false;
    }

}
