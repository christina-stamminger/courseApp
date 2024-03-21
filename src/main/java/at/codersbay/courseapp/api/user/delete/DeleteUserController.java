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

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> delete(
            @PathVariable
            long id) {

        userRepository.deleteById(id);

        Optional<User> optionalUser = userRepository.findById(id);

        ResponseBody responseBody = new ResponseBody();

        if(optionalUser.isPresent()) {
            responseBody.addErrorMessage("Could not delete user by Id: " + id + "!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        } else {
            responseBody.addMessage("Deleted user by id " + id + " successfully!");
            return new ResponseEntity<>(responseBody, HttpStatus.ACCEPTED);
        }
    }
}
