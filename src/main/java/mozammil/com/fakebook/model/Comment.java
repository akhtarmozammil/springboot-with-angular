package mozammil.com.fakebook.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false, updatable = false)
    private Long post_id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

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
