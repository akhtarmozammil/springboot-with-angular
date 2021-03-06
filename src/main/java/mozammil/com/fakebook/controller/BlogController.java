package mozammil.com.fakebook.controller;

import mozammil.com.fakebook.dto.PostContentDto;
import mozammil.com.fakebook.model.Comment;
import mozammil.com.fakebook.model.Post;
import mozammil.com.fakebook.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostContentDto>> getAllPost (){
        List<PostContentDto> posts = blogService.findPostWithTitleAndContent();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/all/posts/with/comments")
    public ResponseEntity<List<Post>> getAllPostWithComments (){
        List<Post> posts = blogService.findAllPost();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Post> getPostById (@PathVariable("id") Long id){
        Post post = blogService.findPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping("/addPost")
    public ResponseEntity<Post> addPost(@RequestBody Post post){
        Post newPost = blogService.addPost(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @PutMapping("/updatePost")
    public ResponseEntity<String> updatePost(@RequestBody PostContentDto post){
        blogService.updatePost(post);
        String response = "Updated";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<?> deletePostById (@PathVariable("id") Long id){
        blogService.deletePostById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Now for Comment

    @GetMapping("/allComments")
    public ResponseEntity<List<Comment>> getAllComments (){
        List<Comment> comments = blogService.findAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("post/{id}/allComments")
    public ResponseEntity<List<Comment>> getAllCommentByPostId (@PathVariable("id") Long id){
        List<Comment> comments = blogService.findAllCommentByPostId(id);
        return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<Comment> getCommentById (@PathVariable("id") Long id){
        Comment comment = blogService.findCommentById(id);
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }

    @PostMapping("/post/{postId}/addComment")
    public ResponseEntity<Comment> addCommentToPostId(@PathVariable Long postId, @RequestBody Comment comment){
        Comment newComment = blogService.addComment(postId,comment);
        return new ResponseEntity<Comment>(newComment, HttpStatus.CREATED);
    }

    @PutMapping("/post/updateComment")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment){
        try{
            blogService.findCommentById(comment.getId());
            Comment updateComment = blogService.updateComment(comment);
            return new ResponseEntity<>(updateComment, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteComment/{id}")
    public ResponseEntity<?> deleteCommentById (@PathVariable("id") Long id){
        blogService.deleteCommentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
