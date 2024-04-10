package at.codersbay.courseapp.api.course.create;

import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.UserResponseBody;
import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.course.CourseRepository;
import at.codersbay.courseapp.api.course.CourseResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/course")
public class CreateCourseController {

    @Autowired
    private CreateCourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    /**
     * Creates a new course based on the data provided in the {@link CreateCourseDTO}.
     *
     * @param createCourseDTO An object containing the data needed to create the course.
     * @return A {@link ResponseEntity} containing the newly created course in the response body,
     * along with an appropriate HTTP status. If the provided DTO is {@code null}, returns
     * {@link HttpStatus#BAD_REQUEST}. If a course with the same ID already exists, returns
     * {@link HttpStatus#CONFLICT}. If the course creation is successful, returns
     * {@link HttpStatus#CREATED}.
     */
    @PostMapping("/create") // Specify the endpoint path for creating a new course
    public ResponseEntity<CourseResponseBody> create(@RequestBody CreateCourseDTO createCourseDTO) {
        try {
            if (createCourseDTO == null) {
                CourseResponseBody response = new CourseResponseBody();
                response.addErrorMessage("Post body is empty.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            // Check if a course with the provided ID already exists
            Optional<Course> courseOptional = courseRepository.findById(createCourseDTO.getId());
            if (courseOptional.isPresent()) {
                CourseResponseBody response = new CourseResponseBody();
                response.addErrorMessage("Course with ID " + createCourseDTO.getId() + " already exists.");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT); // Return conflict status
            }

            // Create a new course using the provided DTO data
            Course newCourse = courseService.create(createCourseDTO.getTitle(), createCourseDTO.getDescription(), createCourseDTO.getMaxUsers());

            // Return the newly created course in the response body
            return new ResponseEntity<>(new CourseResponseBody(newCourse), HttpStatus.CREATED);
        } catch (EmptyCourseException e) {
            // Handle empty course exception
            CourseResponseBody response = new CourseResponseBody();
            response.addErrorMessage("Course creation failed: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}