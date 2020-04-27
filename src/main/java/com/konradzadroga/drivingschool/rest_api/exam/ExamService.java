package com.konradzadroga.drivingschool.rest_api.exam;

import com.konradzadroga.drivingschool.rest_api.user.User;
import com.konradzadroga.drivingschool.rest_api.user.UserService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class ExamService {

    private ExamRepository examRepository;
    private UserService userService;

    public ExamService(ExamRepository examRepository, UserService userService) {
        this.examRepository = examRepository;
        this.userService = userService;
    }

    public List<ExamDTO> findAllExams() {
        List<Exam> exams = examRepository.findAll();
        List<ExamDTO> examDTOs = new ArrayList<>();

        exams.forEach(exam -> {
            examDTOs.add(new ExamDTO(exam));
        });

        return examDTOs;
    }

    public List<ExamDTO> findAllByCourseId(int id) {
        List<Exam> exams = examRepository.findAllByCourseId(id);
        List<ExamDTO> examDTOs = new ArrayList<>();

        exams.forEach(exam -> {
            examDTOs.add(new ExamDTO(exam));
        });

        return examDTOs;
    }

    public List<ExamDTO> findAllByStudentUsername(String username) {
        List<Exam> exams = examRepository.findAllByStudentUsername(username);
        List<ExamDTO> examDTOs = new ArrayList<>();

        exams.forEach(exam -> {
            examDTOs.add(new ExamDTO(exam));
        });

        return examDTOs;
    }

    public List<ExamDTO> findAllByInstructorUsername(String username) {
        List<Exam> exams = examRepository.findAllByInstructorUsername(username);
        List<ExamDTO> examDTOs = new ArrayList<>();

        exams.forEach(exam -> {
            examDTOs.add(new ExamDTO(exam));
        });

        return examDTOs;
    }

    private Exam findById(long id) {
        Exam exam = examRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Exam not found")
        );
        return exam;
    }

    public ExamDTO signInForAnExam(long id) {
        User user = userService.getCurrentUser();
        Exam exam = findById(id);

        exam.setStudent(user);
        exam.setOccupied(true);
        examRepository.save(exam);

        return new ExamDTO(exam);
    }

}
