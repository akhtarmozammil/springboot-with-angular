package mozammil.com.fakebook.repository;

import mozammil.com.fakebook.dto.PostContentDto;
import mozammil.com.fakebook.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {

    @Query("SELECT new mozammil.com.fakebook.dto.PostContentDto(p.id, p.title, p.content, p.createdAt, p.updatedAt) FROM Post p")
    public List<PostContentDto> allPostWithTitleAndContent();

    @Transactional
    @Modifying
    @Query("UPDATE Post p SET p.title = :title, p.content = :content, p.updatedAt = :updatedAt WHERE p.id = :postId")
    public void updatePost(@Param("postId") Long id, @Param("title") String Title, @Param("content") String Content, @Param("updatedAt") Date updatedAt);
}
