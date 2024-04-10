package at.codersbay.courseapp.api.user.delete;

import at.codersbay.courseapp.api.ResponseBody;
import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user/")
public class DeleteUserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Deletes a user by id.
     *
     * @param id the ID of the user to delete.
     * @return a ResponseEntity with a ResponseBody indicating the result of the deletion operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> delete(
            @PathVariable
            long id) {
        // Find the user by id from repository
        Optional<User> optionalUser = userRepository.findById(id);

        // Create a response body object
        ResponseBody responseBody = new ResponseBody();
        // If no user found by id, add error message
        if(optionalUser.isEmpty()) {
            responseBody.addErrorMessage("Could not delete user by Id: " + id + "!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        } else {
            // If user found by id, delete user and send message
            userRepository.deleteById(id);
            responseBody.addMessage("Deleted user by id " + id + " successfully!");
            return new ResponseEntity<>(responseBody, HttpStatus.ACCEPTED);
        }
    }
}
