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

@RestController
@RequestMapping("/api/course")
public class CreateCourseController {

    @Autowired
    private CreateCourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public ResponseEntity<CourseResponseBody> create(@RequestBody CreateCourseDTO createCourseDTO) throws EmptyCourseException {

        if (createCourseDTO == null) {
            CourseResponseBody response = new CourseResponseBody();
            response.addErrorMessage("Post body is empty.");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        List<Course> courses = this.courseRepository.findByTitle(createCourseDTO.getTitle());

        Course course = this.courseService.create(createCourseDTO.getId(), createCourseDTO.getTitle(), createCourseDTO.getDescription(), createCourseDTO.getMaxUsers());

        return new ResponseEntity<>(new CourseResponseBody(course), HttpStatus.OK);
    }
}
