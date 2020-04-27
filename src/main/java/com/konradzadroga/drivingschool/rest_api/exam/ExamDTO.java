package com.konradzadroga.drivingschool.rest_api.exam;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ExamDTO {

    private long id;
    private Date dateOfExam;
    private String studentUsername;
    private String studentNameAndSurname;
    private String instructorUsername;
    private String instructorNameAndSurname;
    private int courseId;
    private boolean occupied;
    private boolean passed;

    public ExamDTO(Exam exam) {
        this.id = exam.getId();
        this.dateOfExam = exam.getDateOfExam();
        if (exam.getStudent() != null) {
            this.studentUsername = exam.getStudent().getUsername();
            this.studentNameAndSurname = exam.getStudent().getName() + " " + exam.getStudent().getSurname();
        } else {
            this.studentUsername = "";
            this.studentNameAndSurname = "";
        }
        if (exam.getInstructor() != null) {
            this.instructorUsername = exam.getInstructor().getUsername();
            this.instructorNameAndSurname = exam.getInstructor().getName() + " " + exam.getInstructor().getSurname();
        } else {
            this.instructorUsername = "";
            this.instructorNameAndSurname = "";
        }
        this.courseId = exam.getCourse().getId();
        this.occupied = exam.getOccupied();
        this.passed = exam.getPassed();
    }

}
