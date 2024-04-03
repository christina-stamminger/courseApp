package at.codersbay.courseapp.api.booking.create;

import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.UserResponseBody;
import at.codersbay.courseapp.api.course.Course;

public class BookingDTO {

    private long id;

    private User user;
    private Course course;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
