package at.codersbay.courseapp.api.course;

import at.codersbay.courseapp.api.booking.Booking;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="TB_COURSE")
public class Course {

    @Id
    @GeneratedValue(generator = "course-sequence-generator")
    @GenericGenerator(
            name = "course-sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "course_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private long id;

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @Column
    private Set<Booking> bookings = new HashSet<>();


    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private int maxUsers;

    public Course(){

   }

    public Course(Set<Booking> bookings, String title, String description, int maxUsers) {
        this.bookings = bookings;
        this.title = title;
        this.description = description;
        this.maxUsers = maxUsers;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxUsers() {
        return maxUsers;
    }


    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

}