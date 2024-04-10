package at.codersbay.courseapp.api.booking.get;

import at.codersbay.courseapp.api.booking.Booking;
import at.codersbay.courseapp.api.booking.BookingRepository;
import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.course.CourseRepository;
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
@RequestMapping("/api/booking/")
public class GetBookingController {

    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Retrieves all bookings stored in the database.
     *
     * @return A {@link ResponseEntity} containing a list of bookings with an appropriate HTTP status.
     * If no bookings are found, returns {@link HttpStatus#NO_CONTENT}. If bookings are found,
     * returns a {@link ResponseEntity} with the list of bookings and {@link HttpStatus#OK}.
     */
    @GetMapping("/")
    public ResponseEntity<List<Booking>> getAll() {

        List<Booking> bookings = bookingRepository.findAll();

        if (bookings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    /**
     * Retrieves a booking by id.
     *
     * @param id The unique identifier of the booking to retrieve.
     * @return A {@link ResponseEntity} containing the booking if found, or {@link HttpStatus#NO_CONTENT}
     * if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getById(
            @PathVariable
            long id) {
        Optional<Booking> optionalBooking = this.bookingRepository.findById(id);

        if (!optionalBooking.isPresent()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        Booking booking = optionalBooking.get();

        return ResponseEntity.ok(booking);
    }
}
