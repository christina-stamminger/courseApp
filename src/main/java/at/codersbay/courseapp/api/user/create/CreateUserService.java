package at.codersbay.courseapp.api.user.create;

import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.Optional;

@Service
public class CreateUserService {

    @Autowired
    private UserRepository userRepository;

    public User create(long id, String userName, String password, String firstName, String lastName, String email)
            throws EmptyUserException {

        if (StringUtils.isEmpty(userName)) {
            throw new EmptyUserException("User name cannot be empty.");
        } else if (StringUtils.isEmpty(password)) {
            throw new EmptyUserException("Password cannot be empty.");
        } else if (StringUtils.isEmpty(firstName)) {
            throw new EmptyUserException("First name cannot be empty.");
        } else if (StringUtils.isEmpty(lastName)) {
            throw new EmptyUserException("Last name cannot be empty.");
        }

        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        return this.userRepository.save(user);
    }

    public User getUserById(long id) throws UserNotFoundException {
        if (id <= 0) { // Checking if userId is not null and is a valid ID
            throw new IllegalArgumentException("User ID must be greater than zero.");
        }

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFoundException("User by id not found!");
        }
    }
}
