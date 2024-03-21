package at.codersbay.courseapp.api.booking;

import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

/*
    @PostMapping
    public ResponseEntity<Booking> bookCourse(@RequestBody BookingDTO bookingCourseDTO) {
        User user = bookingCourseDTO.getUser();
        Course course = bookingCourseDTO.getCourse();

        Booking booking = bookingService.createBooking(user, course);

        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

 */
}