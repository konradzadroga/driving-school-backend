package com.konradzadroga.drivingschool.rest_api.activity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ActivityController {

    private ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @RequestMapping(value = "/activities/courses/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<ActivityDTO>> findActivitiesByCourse(@PathVariable int id) {
        List<ActivityDTO> activities = activityService.findActivitiesByCourse(id);

        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @RequestMapping(value = "/activities/courses/{id}/student", method = RequestMethod.GET)
    public ResponseEntity<List<ActivityDTO>> findActivitiesByCourseWhereUserIsSignedUp(@PathVariable int id) {
        List<ActivityDTO> activities = activityService.findActivitiesByCourseWhereUserIsSignedUp(id);

        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @RequestMapping(value = "/activities/courses/{id}/mine", method = RequestMethod.GET)
    public ResponseEntity<List<ActivityDTO>> findActivitiesByCourseAndUser(@PathVariable int id) {
        List <ActivityDTO> activities = activityService.findAllByCourseIdAndStudentUsername(id);

        return new ResponseEntity<>(activities, HttpStatus.OK);
    }


    @RequestMapping(value = "/activities/courses/{id}/dates", method = RequestMethod.GET)
    public ResponseEntity<List<Date>> findActivitiesDatesByCourse(@PathVariable int id) {
        List<Date> dates = activityService.findAllCourseActivitiesDates(id);

        return new ResponseEntity<>(dates, HttpStatus.OK);
    }

    @RequestMapping(value = "/activities/courses/{id}/signup", method = RequestMethod.POST)
    public ResponseEntity<ActivityDTO> signUserForActivity(@PathVariable long id) {
        ActivityDTO activity = activityService.signUserForActivity(id);

        return new ResponseEntity<>(activity, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @RequestMapping(value = "/activities/{id}/comment", method = RequestMethod.POST)
    public ResponseEntity<ActivityDTO> addComment(@PathVariable long id, @RequestBody String comment) {
        ActivityDTO activity = activityService.addComment(id, comment);

        return new ResponseEntity<>(activity, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('INSTRUCTOR')")
    @RequestMapping(value = "/activities/{id}/rate/{rate}", method = RequestMethod.POST)
    public ResponseEntity<ActivityDTO> addRate(@PathVariable long id, @PathVariable int rate) {
        ActivityDTO activity = activityService.addRate(id, rate);

        return new ResponseEntity<>(activity, HttpStatus.OK);
    }

}
