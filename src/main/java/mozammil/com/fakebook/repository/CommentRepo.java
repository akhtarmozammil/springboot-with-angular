package mozammil.com.fakebook.repository;

import mozammil.com.fakebook.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Long> {

}
