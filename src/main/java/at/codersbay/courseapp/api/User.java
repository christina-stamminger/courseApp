package at.codersbay.courseapp.api;
import at.codersbay.courseapp.api.course.Course;
import com.fasterxml.jackson.annotation.JsonIgnore;

import at.codersbay.courseapp.api.booking.Booking;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Entity and Table annotations
// Entity represents a table
// Table to specify details
@Entity
@Table(name="TB_USERS")
public class User {

    @Id
    @GeneratedValue(generator = "user-sequence-generator")
    @GenericGenerator(
            name = "user-sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private long id;

    // Annotation comes from the Jackson library and is used to instruct Jackson to ignore the annotated field during serialization and deserialization processes
    @JsonIgnore
    // MappedBy attribute specifies the field in the Booking entity that owns the relationship
    // Any changes made to the User entity are cascaded to associated Booking entities
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)


    @Column
    private Set<Booking> bookings = new HashSet<>();

    @Column(unique = true)
    private String userName;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true)
    @Email(message = "Email is not valid", regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    @NotEmpty(message = "Email cannot be empty")
    private String email;


    // Default constructor, required by JPA
    public User() {

    }

    // Parametrized constructor
    public User(String userName, String password, String firstName, String lastName, String email) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getter and Setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

}
