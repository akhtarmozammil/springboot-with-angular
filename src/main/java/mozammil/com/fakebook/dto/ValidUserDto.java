package mozammil.com.fakebook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mozammil.com.fakebook.model.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ValidUserDto {
    private Boolean isEmailPresent;
    private User user;
}
