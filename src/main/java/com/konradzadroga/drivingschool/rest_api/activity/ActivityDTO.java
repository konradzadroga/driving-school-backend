package com.konradzadroga.drivingschool.rest_api.activity;
import com.konradzadroga.drivingschool.rest_api.course.CourseDTO;
import com.konradzadroga.drivingschool.rest_api.user.UserDTO;
import lombok.Data;
import java.util.Date;

@Data
public class ActivityDTO {

    private Date dateOfActivity;
    private int rate;
    private CourseDTO course;
    private UserDTO instructor;
    private UserDTO student;

    public ActivityDTO(Activity activity) {
        this.dateOfActivity = activity.getDateOfActivity();
        this.rate = activity.getRate();
        this.course = new CourseDTO(activity.getCourse());
        this.instructor = new UserDTO(activity.getInstructor());
        if (activity.getStudent() != null) {
            this.student = new UserDTO(activity.getStudent());
        } else {
            this.student = null;
        }
    }

}
