package at.codersbay.courseapp.api.user.create;

import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.booking.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CreateUserDTO {

    private long id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;


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

}
