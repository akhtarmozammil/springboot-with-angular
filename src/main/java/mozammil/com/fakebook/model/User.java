package mozammil.com.fakebook.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Locale;

@Data
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "user_name",nullable = false)
    private String userName;

    @Column(nullable = false, updatable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @PrePersist
    protected void onCreate(){
        email = email.toLowerCase(Locale.ROOT);
    }
}
