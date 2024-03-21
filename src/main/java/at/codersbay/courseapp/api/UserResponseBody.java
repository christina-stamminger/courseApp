package at.codersbay.courseapp.api;

public class UserResponseBody extends ResponseBody {

    private User user;

    public UserResponseBody(){

    }
    public UserResponseBody(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
