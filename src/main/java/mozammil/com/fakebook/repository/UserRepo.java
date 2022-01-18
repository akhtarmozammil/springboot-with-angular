package mozammil.com.fakebook.repository;

import mozammil.com.fakebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {
    @Query("SELECT u FROM User u where u.email = :email")
    Optional<User> findByEmail(@Param("email") String emailId);
}
