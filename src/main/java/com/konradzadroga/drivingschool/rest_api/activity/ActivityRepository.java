package com.konradzadroga.drivingschool.rest_api.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findAllByCourseId(int id);
    List<Activity> findAllByCourseIdAndStudentNotNull(int id);
    List<Activity> findAllByCourseIdOrderByDateOfActivity(int id);
    List<Activity> findAllByCourseIdAndStudentUsername(int id, String username);

}
