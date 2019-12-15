package com.konradzadroga.drivingschool.rest_api.activity;
import com.konradzadroga.drivingschool.rest_api.user.User;
import com.konradzadroga.drivingschool.rest_api.user.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ActivityService {

    private ActivityRepository activityRepository;
    private UserService userService;

    public ActivityService(ActivityRepository activityRepository, UserService userService) {
        this.activityRepository = activityRepository;
        this.userService = userService;
    }

    public Activity findActivityById(long id) {
        Activity activity = activityRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Activity not found")
        );

        return activity;
    }

    public List<ActivityDTO> findActivitiesByCourse(int id) {
        List<Activity> activities = activityRepository.findAllByCourseId(id);
        List<ActivityDTO> activityDTOs = new ArrayList<>();
        activities.forEach(activity -> activityDTOs.add(new ActivityDTO(activity)));
        return activityDTOs;
    }

    public List<ActivityDTO> findActivitiesByCourseWhereUserIsSignedUp(int id) {
        List<Activity> activities = activityRepository.findAllByCourseIdAndStudentNotNull(id);
        List<ActivityDTO> activityDTOs = new ArrayList<>();
        activities.forEach(activity -> activityDTOs.add(new ActivityDTO(activity)));
        return activityDTOs;
    }

    public List<Date> findAllCourseActivitiesDates(int id) {
        List<Activity> activities = activityRepository.findAllByCourseIdOrderByDateOfActivity(id);
        List<Date> activityDates = new ArrayList<>();
        activities.forEach(activity -> activityDates.add(activity.getDateOfActivity()));
        return activityDates;
    }


    public ActivityDTO signUserForActivity(long id) {
        User user = userService.getCurrentUser();
        Activity activity = findActivityById(id);
        activity.setStudent(user);
        activityRepository.save(activity);
        ActivityDTO activityDTO = new ActivityDTO(activity);

        return activityDTO;
    }

    public ActivityDTO addComment(long id, String comment) {
        Activity activity = findActivityById(id);
        activity.setComment(comment);
        ActivityDTO activityDTO = new ActivityDTO(activity);
        activityRepository.save(activity);

        return activityDTO;
    }

    public ActivityDTO addRate(long id, int rate) {
        Activity activity = findActivityById(id);
        activity.setRate(rate);
        ActivityDTO activityDTO = new ActivityDTO(activity);
        activityRepository.save(activity);

        return activityDTO;
    }

    public List<ActivityDTO> findAllByCourseIdAndStudentUsername(int id) {
        String username = userService.getSignedInUser().getUsername();
        List<Activity> activities = activityRepository.findAllByCourseIdAndStudentUsername(id, username);
        List<ActivityDTO> activityDTOs = new ArrayList<>();

        activities.forEach(activity -> {
            activityDTOs.add(new ActivityDTO(activity));
        });

        return activityDTOs;
    }

}
