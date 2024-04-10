package at.codersbay.courseapp.api.booking.create;

import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.booking.Booking;
import at.codersbay.courseapp.api.booking.BookingRepository;
import at.codersbay.courseapp.api.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class BookingService {


    @Autowired
    private BookingRepository bookingRepository;

    @Transactional
    public Booking createBooking(User user, Course course) {
        try {
            // Check if user and course are not null
            if (user == null) {
                throw new IllegalArgumentException("User cannot be empty");
            }
            if (course == null) {
                throw new IllegalArgumentException("Course cannot be empty");
            }

            Booking booking = new Booking();
            booking.setUser(user);
            booking.setCourse(course);
            LocalDateTime bookedOn = LocalDateTime.now();
            booking.setBookedOn(bookedOn); // Set LocalDateTime directly

            // Save the booking to the database
            return bookingRepository.save(booking);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create booking");
        }
    }

}
