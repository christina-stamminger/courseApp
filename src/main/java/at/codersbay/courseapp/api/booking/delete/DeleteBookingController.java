package at.codersbay.courseapp.api.booking.delete;

import at.codersbay.courseapp.api.ResponseBody;
import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.UserRepository;
import at.codersbay.courseapp.api.booking.Booking;
import at.codersbay.courseapp.api.booking.BookingRepository;
import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/booking/")
public class DeleteBookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Maps HTTP DELETE and expects a path variable named "id"
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> delete(
            // id parameter should be bound to the value of the "id" path variable
            @PathVariable
            long id) {

        // Trying to find booking by id
        Optional<Booking> optionalBooking = bookingRepository.findById(id);

        ResponseBody responseBody = new ResponseBody();

        // Checks if booking by given id exists
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            // Delete the booking
            bookingRepository.deleteById(booking.getId());

            responseBody.addMessage("Deleted booking by id " + id + " successfully!");
            return new ResponseEntity<>(responseBody, HttpStatus.ACCEPTED);
        } else {
            responseBody.addErrorMessage("Could not delete booking by Id: " + id + "!");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }
    }
}
