package at.codersbay.courseapp.api;

// Response body containing user data
public class UserResponseBody extends ResponseBody {

    // User object to be included in response
    private User user;

    // Default constructor
    public UserResponseBody(){
    }

    // Constructor with user parameter
    public UserResponseBody(User user) {
        this.user = user;
    }

    // Getter
    public User getUser() {
        return user;
    }

    // Setter
    public void setUser(User user) {
        this.user = user;
    }
}
