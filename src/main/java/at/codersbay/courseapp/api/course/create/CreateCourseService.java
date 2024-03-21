package at.codersbay.courseapp.api.course.create;

import at.codersbay.courseapp.api.User;
import at.codersbay.courseapp.api.UserRepository;
import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.course.CourseRepository;
import at.codersbay.courseapp.api.user.create.EmptyUserException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            course.setId(id);
            course.setTitle(title);
            course.setDescription(description);
            course.setMaxUsers(maxUsers);

            //users.add(user);

            return this.courseRepository.save(course);
        }

}
