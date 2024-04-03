package at.codersbay.courseapp.api.course.create;

import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.UserRepository;
import at.codersbay.courseapp.api.booking.BookingRepository;
import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.course.CourseRepository;
import at.codersbay.courseapp.api.user.create.CreateUserService;
import at.codersbay.courseapp.api.user.create.EmptyUserException;
import at.codersbay.courseapp.api.user.create.UserNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
    public class CreateCourseService {

    @Autowired
    private CourseRepository courseRepository;


        public Course create(long id, String title, String description, Integer maxUsers)
                throws EmptyCourseException {

            if (StringUtils.isEmpty(title)) {
                throw new EmptyCourseException("Title cannot be empty.");
            } if (StringUtils.isEmpty(description)) {
                throw new EmptyCourseException("Description cannot be empty.");
            } else if (maxUsers == null) {
                throw new EmptyCourseException("Maximum of users cannot be empty.");
            }

            Course course = new Course();
            course.setTitle(title);
            course.setDescription(description);
            course.setMaxUsers(maxUsers);

            //users.add(user);

            return this.courseRepository.save(course);
        }

    public Course getId(long id) throws CourseNotFoundException {
        if (id <= 0) { // Checking if userId is not null and is a valid ID
            throw new IllegalArgumentException("Course ID must be greater than zero.");
        }

        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            return optionalCourse.get();
        } else {
            throw new CourseNotFoundException("Course by user Id not found!");
        }
    }

}
