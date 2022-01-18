package mozammil.com.fakebook.service;


import mozammil.com.fakebook.dto.PostContentDto;
import mozammil.com.fakebook.exception.CommentNotFoundException;
import mozammil.com.fakebook.exception.PostNotFoundException;
import mozammil.com.fakebook.model.Comment;
import mozammil.com.fakebook.model.Post;
import mozammil.com.fakebook.repository.CommentRepo;
import mozammil.com.fakebook.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {

    private final PostRepo postRepo;
    private final CommentRepo commentRepo;

    @Autowired
    public BlogService(PostRepo postRepo, CommentRepo commentRepo) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    public Post addPost(Post post){
        return postRepo.save(post);
    }

    public List<Post> findAllPost(){
        return postRepo.findAll();
    }

    public void deletePostById(Long id){
        Post post = postRepo.getById(id);
        postRepo.delete(post);
    }

    public Post findPostById(Long id){
        return postRepo.findById(id).
                orElseThrow(() -> new PostNotFoundException("Post id: " + id + " is not found."));
    }

    public List<PostContentDto> findPostWithTitleAndContent(){
        return postRepo.allPostWithTitleAndContent();
    }

    public Comment addComment(Long id, Comment comment){

        comment.setPost_id(id);
        return commentRepo.save(comment);
    }

    public List<Comment> findAllCommentByPostId(Long id){
        List<Comment> commentsToAPost = commentRepo.findAllCommentByPostId(id);
        return commentsToAPost;
    }

    public Comment findCommentById(Long id){
        return commentRepo.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment not found for id: " + id));
    }

    public void deleteCommentById(Long id){
        commentRepo.deleteById(id);
    }

    public List<Comment> findAllComments() {
        return commentRepo.findAll();
    }

    public Comment updateComment(Comment comment) {
        return commentRepo.save(comment);
    }

    public void updatePost(PostContentDto post) {
        post.setUpdatedAt(new Date());
        postRepo.updatePost(post.getId(), post.getTitle(), post.getContent(), post.getUpdatedAt());
    }
}

