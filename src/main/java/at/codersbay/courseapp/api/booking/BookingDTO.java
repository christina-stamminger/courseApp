package at.codersbay.courseapp.api.booking;

import at.codersbay.courseapp.api.User;

public class BookingDTO {

    private long id;
    private long courseId;
    private long userId;

    private String userName;


/* CURRENT DATE AND TIME!!
    private Object LocalDateTime;
    LocalDateTime localDateTime = LocalDateTime.now();
    LocalDate localDate = localDateTime.toLocalDate();
    */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getUserId() {
        return userId;
    }

    public User setUserId(long userId) {
        this.userId = userId;
        return null;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
