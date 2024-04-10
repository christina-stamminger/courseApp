package at.codersbay.courseapp.api.user.update;

import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.UserRepository;
import at.codersbay.courseapp.api.UserResponseBody;
import at.codersbay.courseapp.api.booking.Booking;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

// Marks a class as restful controller
// Indicating that its methods handle HTTP requests and directly return the response body, typically in JSON or XML format
@RestController
// Defines the base URI path for request mapping methods within the controller class
@RequestMapping("/api/user")

public class UpdateUserController {

    // Injecting user repository dependency
    @Autowired
    UserRepository userRepository;

    /**
     * Updates a user by id based on the provided data in the {@link UpdateUserDTO}.
     *
     * @param updateUserDTO An object containing the updated user data.
     * @return A {@link ResponseEntity} containing the updated user information in the response body,
     * along with an HTTP status indicating the success or failure of the operation.
     * If the provided {@code updateUserDTO} is {@code null}, returns a {@link ResponseEntity} with
     * {@link HttpStatus#BAD_REQUEST}. If the user is not found based on the provided ID, returns a
     * {@link ResponseEntity} with {@link HttpStatus#BAD_REQUEST}.
     */
    @PutMapping
    public ResponseEntity<UserResponseBody> update(
            @RequestBody
            UpdateUserDTO updateUserDTO) {

        // Check if updateUserDTO is null
        if (updateUserDTO == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        // Finding user by id from repository
        Optional<User> optionalUser = this.userRepository.findById(updateUserDTO.getId());

        // If user is not found, return a bad request response
        if (optionalUser.isEmpty()) {
            UserResponseBody response = new UserResponseBody();
            response.addErrorMessage("Could not find user by userId '" + updateUserDTO.getId());

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        // Retrieving user object
        User user = optionalUser.get();

        // Updating user's username if provided in updateUserDTO
        if(!StringUtils.isEmpty(updateUserDTO.getUserName())) {
            user.setUserName(updateUserDTO.getUserName());
        }

        if(!StringUtils.isEmpty(updateUserDTO.getFirstName())) {
            user.setFirstName(updateUserDTO.getFirstName());
        }

        if(!StringUtils.isEmpty(updateUserDTO.getLastName())) {
            user.setLastName(updateUserDTO.getLastName());
        }

        if(!StringUtils.isEmpty(updateUserDTO.getEmail())) {
            user.setEmail(updateUserDTO.getEmail());
        }

        // Saving the updated user to the repository
        this.userRepository.save(user);

        // Creating a response body with updated user information
        UserResponseBody response = new UserResponseBody();
        response.setUser(user);

        // Returning a response entity with OK status and updated user information
        return ResponseEntity.ok(response);
    }

}