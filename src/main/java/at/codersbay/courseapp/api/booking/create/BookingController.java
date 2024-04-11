package at.codersbay.courseapp.api.booking.create;

import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.UserRepository;
import at.codersbay.courseapp.api.UserResponseBody;
import at.codersbay.courseapp.api.booking.Booking;
import at.codersbay.courseapp.api.booking.BookingRepository;
import at.codersbay.courseapp.api.booking.BookingResponseBody;
import at.codersbay.courseapp.api.booking.create.EmptyBookingException;
import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.course.CourseRepository;
import at.codersbay.courseapp.api.course.create.EmptyCourseException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    /**
     * Processes a booking request for a user for a course.
     *
     * @param bookingDTO An object containing the data required for the booking operation,
     * including the user and course details.
     * @return A {@link ResponseEntity} containing the newly created booking if successful,
     * or an appropriate error response if the booking could not be processed.
     * Returns {@link HttpStatus#BAD_REQUEST} if the user has already booked the course,
     * if the course is fully booked, or if the booking already exists.
     * Returns {@link HttpStatus#CONFLICT} if the booking with the provided ID already exists.
     * Returns {@link HttpStatus#INTERNAL_SERVER_ERROR} if an unexpected error occurs during processing.
     */
    @PostMapping
    public ResponseEntity<?> book(@RequestBody BookingDTO bookingDTO) {
        try {
            // Extract necessary data from the bookingDTO
            User user = bookingDTO.getUser();
            Course course = bookingDTO.getCourse();

            // Check if the user has already booked this course
            boolean userAlreadyBooked = bookingRepository.existsByUserIdAndCourseId(user.getId(), course.getId());
            if (userAlreadyBooked) {
                // User has already booked this course
                BookingResponseBody response = new BookingResponseBody();
                response.addErrorMessage("User has already booked this course.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            // Retrieve the bookings for the specific course
            List<Booking> bookingsForCourse = bookingRepository.findAllByCourseId(course.getId());

            // Check if the course has reached its maximum users
            if (bookingsForCourse.size() >= courseRepository.findById(course.getId()).get().getMaxUsers()) {
                // if (bookingsForCourse.size() >= course.getMaxUsers()) {

                // Course is fully booked
                BookingResponseBody response = new BookingResponseBody();
                response.addErrorMessage("Course is fully booked. Cannot make a booking.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            // Check if the booking already exists
            Optional<Booking> optionalBooking = bookingRepository.findById(bookingDTO.getId());
            if (optionalBooking.isPresent()) {
                // Booking with the provided ID already exists
                BookingResponseBody response = new BookingResponseBody();
                response.addErrorMessage("Booking with ID " + bookingDTO.getId() + " already exists.");
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            // Delegate the creation of the booking to the service layer
            Booking booking = bookingService.createBooking(user, course);

            // Return the created booking in the response
            return new ResponseEntity<>(booking, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle any exceptions/validation errors
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}