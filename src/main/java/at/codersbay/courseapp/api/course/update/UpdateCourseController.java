package at.codersbay.courseapp.api.course.update;

import at.codersbay.courseapp.api.UserResponseBody;
import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.course.CourseRepository;
import at.codersbay.courseapp.api.course.CourseResponseBody;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/course")
public class UpdateCourseController {

    @Autowired
    CourseRepository courseRepository;

    @PutMapping
    public ResponseEntity<CourseResponseBody> update(

            @RequestBody
            UpdateCourseDTO updateCourseDTO) {

        if (updateCourseDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Course> optionalCourse = this.courseRepository.findById(updateCourseDTO.getId());

        if (optionalCourse.isEmpty()) {

            CourseResponseBody response = new CourseResponseBody();
            response.addErrorMessage("Could not find course by id" + updateCourseDTO.getId());

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Course course = optionalCourse.get();

        if(!StringUtils.isEmpty(updateCourseDTO.getTitle())) {
            course.setTitle(updateCourseDTO.getTitle());
        }

        if(!StringUtils.isEmpty(updateCourseDTO.getDescription())) {
            course.setDescription(updateCourseDTO.getDescription());
        }

        if(!ObjectUtils.isEmpty(updateCourseDTO.getMaxUsers())) {
            course.setMaxUsers(updateCourseDTO.getMaxUsers());
        }

        this.courseRepository.save(course);

        CourseResponseBody response = new CourseResponseBody();
        response.setCourse(course);

        return ResponseEntity.ok(response);

    }
}
