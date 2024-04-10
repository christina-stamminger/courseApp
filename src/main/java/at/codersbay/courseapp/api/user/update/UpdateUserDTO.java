package at.codersbay.courseapp.api.user.update;

import at.codersbay.courseapp.api.booking.Booking;

import java.util.Set;

// DTO design pattern used for data transfer
public class UpdateUserDTO {
    private long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    private Set<Booking> bookings;


    // Getter and setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setBookings() {
        this.bookings = bookings;
    }
}


