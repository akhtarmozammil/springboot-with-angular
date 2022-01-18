package mozammil.com.fakebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostContentDto {
    private Long id;
    private String title;
    private String content;
    private Date createdAt;
    private Date updatedAt;
}
