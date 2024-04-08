package at.codersbay.courseapp.api.booking;

import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findById(Long id);

    boolean existsByUserIdAndCourseId(long userId, long courseId);
    //List<Course> findById(long id);

    List<Booking> findAllByCourseId(long id);
}

