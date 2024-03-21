package at.codersbay.courseapp.data;

import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.UserRepository;
import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.course.CourseRepository;
import at.codersbay.courseapp.api.user.create.CreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Service
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreateUserService userService;

    @Autowired
    private CourseRepository courseRepository;


    @PostConstruct
    public void setup() {
        importUsers();
    }


    public void importUsers() {
        List<User> users = this.userRepository.findAll();

        if (users.isEmpty()) {
            User testuser1 = new User();
            testuser1.setUserName("Sarah2001");
            testuser1.setPassword("123456");
            testuser1.setFirstName("Sarah");
            testuser1.setLastName("Mitchel");
            testuser1.setEmail("sarah@gmail.com");
            //testuser1.add(users);

            userRepository.save(testuser1);

            User testuser2 = new User();
            testuser2.setUserName("Mike1998");
            testuser2.setPassword("111111");
            testuser2.setFirstName("Mike");
            testuser2.setLastName("River");
            testuser2.setEmail("mike@gmail.com");
            userRepository.save(testuser2);

            User testuser3 = new User();
            testuser3.setUserName("Susan2004");
            testuser3.setPassword("222222");
            testuser3.setFirstName("Susan");
            testuser3.setLastName("Cocker");
            testuser3.setEmail("susan@gmail.com");
            userRepository.save(testuser3);
        }
    }
}
