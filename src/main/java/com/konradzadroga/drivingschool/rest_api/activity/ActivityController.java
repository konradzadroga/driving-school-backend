package com.konradzadroga.drivingschool.rest_api.activity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/activities/courses/{id}/dates", method = RequestMethod.GET)
    public ResponseEntity<List<Date>> findActivitiesDatesByCourse(@PathVariable int id) {
        List<Date> dates = activityService.findAllCourseActivitiesDates(id);

        return new ResponseEntity<>(dates, HttpStatus.OK);
    }

}
