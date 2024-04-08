package at.codersbay.courseapp.api.user.create;

import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class CreateUserService {

    // Regular expression for basic email validation
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Autowired
    private UserRepository userRepository;

    public User create(long id, String userName, String password, String firstName, String lastName, String email)
            throws EmptyUserException, InvalidEmailException {

        if (StringUtils.isEmpty(userName)) {
            throw new EmptyUserException("User name cannot be empty.");
        } else if (StringUtils.isEmpty(password)) {
            throw new EmptyUserException("Password cannot be empty.");
        } else if (StringUtils.isEmpty(firstName)) {
            throw new EmptyUserException("First name cannot be empty.");
        } else if (StringUtils.isEmpty(lastName)) {
            throw new EmptyUserException("Last name cannot be empty.");
        } else if (StringUtils.isEmpty(email)) {
            throw new EmptyUserException("Email cannot be empty.");
        } else if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidEmailException("Invalid email format.");
        }

        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        //user.setBookings(bookings);

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
