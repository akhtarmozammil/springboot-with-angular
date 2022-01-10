package mozammil.com.fakebook.repository;

import mozammil.com.fakebook.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Long> {

}
