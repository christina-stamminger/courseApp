package at.codersbay.courseapp.api.booking;

import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    /*
    @Transactional
    public Booking createBooking(User user, Course course) {
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setCourse(course);
        booking.setBookedOn(LocalDateTime.now());

        return bookingRepository.save(booking);
    }
*/
}
