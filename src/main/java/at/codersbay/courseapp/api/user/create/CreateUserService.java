package at.codersbay.courseapp.api.user.create;

import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class CreateUserService {

    // Regular expression for basic email validation
    // This regular expression validates email addresses that follow typical formats
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Autowired
    private UserRepository userRepository;

    public User create(long id, String userName, String password, String firstName, String lastName, String email)
            throws EmptyUserException, InvalidEmailException, UserNameExistsException {

        if (StringUtils.isEmpty(userName)) {
            throw new EmptyUserException("User name cannot be empty.");
        } else if (StringUtils.isEmpty(password)) {
            throw new EmptyUserException("Password cannot be empty.");
        } else if (StringUtils.isEmpty(firstName)) {
            throw new EmptyUserException("First name cannot be empty.");
        } else if (!isAlpha(firstName)) {
            throw new IllegalArgumentException("First name should contain only characters.");
        } else if (StringUtils.isEmpty(lastName)) {
            throw new EmptyUserException("Last name cannot be empty.");
        } else if (!isAlpha(lastName)) {
            throw new IllegalArgumentException("Last name should contain characters.");
        } else if (StringUtils.isEmpty(email)) {
            throw new EmptyUserException("Email cannot be empty.");
        } else if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidEmailException("Invalid email format.");
        } else if (userNameExists(userName)) {
            throw new UserNameExistsException("Username already exists.");
        }

        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        return this.userRepository.save(user);
    }

    private boolean userNameExists(String userName) {
        Optional<User> existingUser = userRepository.findByUserName(userName);
        return existingUser.isPresent();
    }

    /*
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
     */
}


