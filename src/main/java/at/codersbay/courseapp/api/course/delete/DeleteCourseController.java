package at.codersbay.courseapp.api.course.delete;

import at.codersbay.courseapp.api.ResponseBody;
import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.UserRepository;
import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

    @RestController
    @RequestMapping("/api/course/")
    public class DeleteCourseController {

        @Autowired
        private CourseRepository courseRepository;

        /**
         * Deletes a course by id.
         *
         * @param id The unique identifier of the course to delete.
         * @return A {@link ResponseEntity} containing a response body with a message indicating the success or failure
         * of the deletion operation, along with an appropriate HTTP status. If the course is successfully deleted,
         * returns {@link HttpStatus#ACCEPTED}. If the course with the specified ID does not exist or could not be deleted,
         * returns {@link HttpStatus#BAD_REQUEST}.
         */
        @DeleteMapping("/{id}")
        public ResponseEntity<ResponseBody> delete(
                @PathVariable
                long id) {

            courseRepository.deleteById(id);

            Optional<Course> optionalCourse = courseRepository.findById(id);

            ResponseBody responseBody = new ResponseBody();

            if(optionalCourse.isPresent()) {
                responseBody.addErrorMessage("Could not delete course by Id: " + id + "!");
                return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
            } else {
                responseBody.addMessage("Deleted course by id " + id + " successfully!");
                return new ResponseEntity<>(responseBody, HttpStatus.ACCEPTED);
            }
        }
    }