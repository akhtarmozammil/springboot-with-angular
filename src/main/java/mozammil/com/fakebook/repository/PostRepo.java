package mozammil.com.fakebook.repository;

import mozammil.com.fakebook.dto.PostContentDto;
import mozammil.com.fakebook.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {

    @Query("SELECT new mozammil.com.fakebook.dto.PostContentDto(p.id, p.title, p.content, p.createdAt, p.updatedAt) FROM Post p")
    public List<PostContentDto> allPostWithTitleAndContent();

}
