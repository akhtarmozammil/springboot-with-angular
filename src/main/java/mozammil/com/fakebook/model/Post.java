package mozammil.com.fakebook.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString
@Entity
@Table(name = "post")
public class Post{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;

    @OneToMany(
            targetEntity = Comment.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private List<Comment> comments = new ArrayList<>();

    @PrePersist
    protected void onCreation(){
        if(createdAt == null){
            createdAt = new Date();
            updatedAt = createdAt;
        }
    }

    @PreUpdate
    protected void onUpdation(){
        updatedAt = new Date();
    }
}