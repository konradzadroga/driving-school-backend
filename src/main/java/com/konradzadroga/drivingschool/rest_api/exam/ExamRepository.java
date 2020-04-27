package com.konradzadroga.drivingschool.rest_api.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    List<Exam> findAllByCourseId(int id);
    List<Exam> findAllByStudentUsername(String username);
    List<Exam> findAllByInstructorUsername(String username);

}
