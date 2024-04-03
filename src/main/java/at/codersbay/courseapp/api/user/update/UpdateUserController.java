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

@RestController
@RequestMapping("/api/user")

public class UpdateUserController {

    @Autowired
    UserRepository userRepository;

    @PutMapping
    public ResponseEntity<UserResponseBody> update(
            @RequestBody
            UpdateUserDTO updateUserDTO) {

        if (updateUserDTO == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Optional<User> optionalUser = this.userRepository.findById(updateUserDTO.getId());

        if (optionalUser.isEmpty()) {
            UserResponseBody response = new UserResponseBody();
            response.addErrorMessage("Could not find user by userId '" + updateUserDTO.getId());

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        User user = optionalUser.get();

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

        this.userRepository.save(user);

        UserResponseBody response = new UserResponseBody();
        response.setUser(user);

        return ResponseEntity.ok(response);
    }

}