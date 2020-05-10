package com.konradzadroga.drivingschool.rest_api.exam;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Data
public class ExamController {

    private ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @RequestMapping(value = "/exams", method = RequestMethod.GET)
    public ResponseEntity<List<ExamDTO>> findAllExams() {
        List<ExamDTO> exams = examService.findAllExams();

        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @RequestMapping(value = "/exams/courses/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<ExamDTO>> findAllByCourseId(@PathVariable int id) {
        List<ExamDTO> exams = examService.findAllByCourseId(id);

        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @RequestMapping(value = "/exams/students/{username}", method = RequestMethod.GET)
    public ResponseEntity<List<ExamDTO>> findAllByStudentUsername(@PathVariable String username) {
        List<ExamDTO> exams = examService.findAllByStudentUsername(username);

        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @RequestMapping(value = "/exams/instructors/{username}", method = RequestMethod.GET)
    public ResponseEntity<List<ExamDTO>> findAllByInstructorId(@PathVariable String username) {
        List<ExamDTO> exams = examService.findAllByInstructorUsername(username);

        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @RequestMapping(value = "/exams/signin/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ExamDTO> signInForAnExam(@PathVariable long id) {
        ExamDTO exam = examService.signInForAnExam(id);

        return new ResponseEntity<>(exam, HttpStatus.OK);
    }

}
