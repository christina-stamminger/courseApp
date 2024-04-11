package at.codersbay.courseapp.api.user.create;

import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.UserResponseBody;
import at.codersbay.courseapp.api.UserRepository;
import at.codersbay.courseapp.api.user.get.GetUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/user")
public class CreateUserController {

    @Autowired
    private CreateUserService userService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new User based on the provided CreateUserDTO.
     *
     * @param createUserDTO DTO with details for creating User
     * @throws EmptyUserException if createUserDTO is empty or null
     * @throws InvalidEmailException if the email provided in createUserDTO is invalid
     * @throws EmailExistsException if the email already exists
     * @throws UserNameExistsException if the username provided in createUserDTO already exists
     */

    @PostMapping
    public ResponseEntity<UserResponseBody> create(@RequestBody CreateUserDTO createUserDTO) throws EmptyUserException, InvalidEmailException, UserNameExistsException, EmailExistsException {
        if (createUserDTO == null) {
            UserResponseBody response = new UserResponseBody();
            response.addErrorMessage("Post body is empty.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<User> userOptional = userRepository.findById(createUserDTO.getId());
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            UserResponseBody response = new UserResponseBody();
            response.addErrorMessage("User with ID " + existingUser.getId() + " already exists.");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        // User does not exist, proceed with creation
        User user = this.userService.create(createUserDTO.getId(), createUserDTO.getUserName(), createUserDTO.getPassword(), createUserDTO.getFirstName(), createUserDTO.getLastName(), createUserDTO.getEmail());

        return new ResponseEntity<>(new UserResponseBody(user), HttpStatus.OK);
    }
}

