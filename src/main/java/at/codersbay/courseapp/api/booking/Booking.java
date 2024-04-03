package at.codersbay.courseapp.api.booking;

import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.course.Course;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="TB_BOOKING")
public class Booking {

    @Id
    @GeneratedValue(generator = "booking-sequence-generator")
    @GenericGenerator(
            name = "booking-sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "booking"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


    private LocalDateTime bookedOn;


    public long getId() {
        return id;
    }

    public void id(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {return course;}
    public void setCourse(Course course) {
        this.course = course;
    }



    public LocalDateTime getBookedOn() {
        return bookedOn;
    }

    public void setBookedOn(LocalDateTime bookedOn) {
        this.bookedOn = bookedOn;
    }



}
