package at.codersbay.courseapp.api.user.get;

import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/user/")
public class GetUserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retrieves all users stored in the database.
     *
     * @return A {@link ResponseEntity} containing a list of users with an appropriate HTTP status.
     * If no users are found, returns {@link HttpStatus#NO_CONTENT}. If users are found,
     * returns a {@link ResponseEntity} with the list of users and {@link HttpStatus#OK}.
     */
    @GetMapping
    public ResponseEntity<List<User>> getAll() {

        // Retrieve all users from the repository
        List<User> users = userRepository.findAll();

        // If no users found, return a response status
        if(users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // If users found, return a response with the list of users and OK status
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Retrieves a user by id.
     *
     * @param id The unique identifier of the user to retrieve.
     * @return A {@link ResponseEntity} containing the user if found, or {@link HttpStatus#NO_CONTENT}
     * if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable long id) {

        // Find user by id from repository
        Optional<User> optionalUser = this.userRepository.findById(id);

        if(!optionalUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        // If user found, retrieve user object
        User user = optionalUser.get();

        // Return response with user object
        return ResponseEntity.ok(user);
    }

}
