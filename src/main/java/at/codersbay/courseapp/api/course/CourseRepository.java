package at.codersbay.courseapp.api.course;

import at.codersbay.courseapp.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByTitle(String title);

}
