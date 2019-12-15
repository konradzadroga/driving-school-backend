package com.konradzadroga.drivingschool.rest_api.activity;
import com.konradzadroga.drivingschool.rest_api.course.CourseDTO;
import com.konradzadroga.drivingschool.rest_api.user.UserDTO;
import lombok.Data;
import java.util.Date;

@Data
public class ActivityDTO {

    private long id;
    private Date dateOfActivity;
    private int rate;
    private String comment;
    private CourseDTO course;
    private UserDTO instructor;
    private UserDTO student;

    public ActivityDTO(Activity activity) {
        this.id = activity.getId();
        this.dateOfActivity = activity.getDateOfActivity();
        this.rate = activity.getRate();
        this.comment = activity.getComment();
        this.course = new CourseDTO(activity.getCourse());
        this.instructor = new UserDTO(activity.getInstructor());
        if (activity.getStudent() != null) {
            this.student = new UserDTO(activity.getStudent());
        } else {
            this.student = null;
        }
    }

}
