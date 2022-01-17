package mozammil.com.fakebook.repository;

import mozammil.com.fakebook.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Long> {

    @Query("SELECT c FROM Comment c WHERE c.post_id = ?1")
    public List<Comment> findAllCommentByPostId(Long id);
}
