package at.codersbay.courseapp.api;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByLastName(String lastName);

    //Fetching User object
    Optional<User> findById(Long id);
}

