package com.konradzadroga.drivingschool.rest_api.activity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ActivityService {

    private ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<ActivityDTO> findActivitiesByCourse(int id) {
        List<Activity> activities = activityRepository.findAllByCourseId(id);
        List<ActivityDTO> activityDTOs = new ArrayList<>();
        activities.forEach(activity -> activityDTOs.add(new ActivityDTO(activity)));
        return activityDTOs;
    }

    public List<Date> findAllCourseActivitiesDates(int id) {
        List<Activity> activities = activityRepository.findAllByCourseId(id);
        List<Date> activityDates = new ArrayList<>();
        activities.forEach(activity -> activityDates.add(activity.getDateOfActivity()));

        return activityDates;
    }

}
