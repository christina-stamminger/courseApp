package at.codersbay.courseapp.api;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// Interface represents a repository for managing user entities
public interface UserRepository extends JpaRepository<User, Long> {

    // Method to fetch a user by their ID
    // Returns an Optional<User> to handle cases where the user might not exist
    Optional<User> findById(Long id);

    // Method to fetch a user by username
    // Returns an Optional<User> (to handle cases where user might not exist)
    Optional<User> findByUserName(String userName);
}

