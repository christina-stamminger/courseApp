package at.codersbay.courseapp.api.booking;

import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.course.Course;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
// Table name for entity in db
@Table(name = "TB_BOOKING")
public class Booking {

    @Id
    @GeneratedValue(generator = "booking-sequence-generator")
    @GenericGenerator(
            name = "booking-sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "booking_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    // Unique identifier for booking
    private long id;

    @ManyToOne
    // Many-to-one relationship (many bookings associated with one user)
    @JoinColumn(name = "user_id", nullable = false)
    // User associated with booking
    private User user;


    @ManyToOne
    // Many-to-one relationship (many booking associated with one course)
    @JoinColumn(name = "course_id", nullable = false)
    // Course associated with booking
    private Course course;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime bookedOn;

    // Getter and Setter
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


    public LocalDateTime getBookedOn() {
        return bookedOn;
    }

    public void setBookedOn(LocalDateTime bookedOn) {
        this.bookedOn = bookedOn;
    }


    public void setFormattedBookedOn(String formattedDateTime) {
    }
}
