package at.codersbay.courseapp.api.course.get;

import at.codersbay.courseapp.api.User;
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
@RequestMapping("/api/course/")
public class GetCourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/")
    public ResponseEntity<List<Course>> getAll() {

        List<Course> courses = courseRepository.findAll();

        if(courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

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
