package at.codersbay.courseapp.api.booking.create;

import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.UserRepository;
import at.codersbay.courseapp.api.UserResponseBody;
import at.codersbay.courseapp.api.booking.Booking;
import at.codersbay.courseapp.api.booking.BookingRepository;
import at.codersbay.courseapp.api.booking.BookingResponseBody;
import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private User user;
    private Course course;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;


    @PostMapping
    public ResponseEntity<Booking> book(@RequestBody BookingDTO bookingDTO) {
        try {
            // Extract necessary data from the bookingDTO
            User user = bookingDTO.getUser();
            Course course = bookingDTO.getCourse();

            // Check if the booking already exists
            Optional<Booking> optionalBooking = bookingRepository.findById(bookingDTO.getId());
            if (optionalBooking.isPresent()) {

                // Booking with the provided ID already exists
                BookingResponseBody response = new BookingResponseBody();
                response.addErrorMessage("Booking with ID " + bookingDTO.getId() + " already exists.");
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            // Validate maxUsers here??


            // Delegate the creation of the booking to the service layer
            Booking booking = bookingService.createBooking(user, course);

            // Return the created booking in the response
            return new ResponseEntity<>(booking, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle any exceptions/ validation errors
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}