package at.codersbay.courseapp.api.user.create;

import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {

    @Autowired
    private UserRepository userRepository;

    public User create(long id, String userName, String password, String firstName, String lastName, String email)
            throws EmptyUserException {

        if (StringUtils.isEmpty(firstName)) {
            throw new EmptyUserException("First name cannot be empty.");
        } else if (StringUtils.isEmpty(lastName)) {
            throw new EmptyUserException("Last name cannot be empty.");
        }

        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        //users.add(user);

        return this.userRepository.save(user);
    }
}
