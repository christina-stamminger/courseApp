package at.codersbay.courseapp.api.course.get;

import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.booking.Booking;
import at.codersbay.courseapp.api.booking.BookingRepository;
import at.codersbay.courseapp.api.booking.create.BookingService;
import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/course/")
public class GetCourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Retrieves all courses stored in the system.
     *
     * @return A {@link ResponseEntity} containing a list of courses with an appropriate HTTP status.
     * If no courses are found, returns {@link HttpStatus#NO_CONTENT}. If courses are found,
     * returns a {@link ResponseEntity} with the list of courses and {@link HttpStatus#OK}.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAll() {

        List<Course> courses = courseRepository.findAll();

        if(courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    /**
     * Retrieves only courses where places are available.
     *
     * @return A {@link ResponseEntity} containing a list of available courses with an appropriate HTTP status.
     * If no available courses are found, returns {@link HttpStatus#NO_CONTENT}. If available courses are found,
     * returns a {@link ResponseEntity} with the list of courses and {@link HttpStatus#OK}.
     */
    @GetMapping("/available")
    public ResponseEntity<List<Course>> getAvailableCourses() {
        List<Course> availableCourses = new ArrayList<>();

        // Retrieve first all courses
        List<Course> allCourses = courseRepository.findAll();

        // Check where places available
        for (Course course : allCourses) {
            List<Booking> bookingsForCourse = bookingRepository.findAllByCourseId(course.getId());
            if(bookingsForCourse.size() < courseRepository.findById(course.getId()).get().getMaxUsers()) {
                availableCourses.add(course);
            }
        }
        if(availableCourses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(availableCourses, HttpStatus.OK);
    }

    /**
     * Retrieves a course by id.
     *
     * @param id The unique identifier of the course to retrieve.
     * @return A {@link ResponseEntity} containing the course if found, or {@link HttpStatus#NO_CONTENT}
     * if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(
            @PathVariable
            long id) {

        Optional<Course> optionalCourse = this.courseRepository.findById(id);

        if(!optionalCourse.isPresent()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        Course course = optionalCourse.get();

        return ResponseEntity.ok(course);
    }
}