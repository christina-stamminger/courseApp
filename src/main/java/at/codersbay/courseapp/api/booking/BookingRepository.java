package at.codersbay.courseapp.api.booking;

import at.codersbay.courseapp.api.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Course, Long> {

        //List<Course> findById(Long courseId);
    public Optional<Course> findById(Long id);

}

