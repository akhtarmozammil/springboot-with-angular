package mozammil.com.fakebook.service;

import mozammil.com.fakebook.dto.ValidUserDto;
import mozammil.com.fakebook.exception.EmailIdNotFoundException;
import mozammil.com.fakebook.exception.PassNotMatchException;
import mozammil.com.fakebook.model.User;
import mozammil.com.fakebook.operation.Authentication;
import mozammil.com.fakebook.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class UserService {
    private UserRepo userRepo;
    private final Authentication authentication = new Authentication();

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public ValidUserDto signUp(User newUser){
        ValidUserDto userInfo = isUserPresent(newUser.getEmail());
        if (!userInfo.getIsEmailPresent()){
            newUser.setPassword(authentication.encodePassword(newUser.getPassword()));
            User userCrested = userRepo.save(newUser);
            System.out.println(userCrested.toString());
            userInfo.setUser(userCrested);
        }else{
            userInfo.setUser(newUser);
            userInfo.getUser().setPassword("");
        }
        return userInfo;
    }

    public Object login(User oldUser){
        ValidUserDto userInfo = isUserPresent(oldUser.getEmail());
        if (userInfo.getIsEmailPresent()){
            boolean isPasswordMatch = authentication.isValidCredential(oldUser.getPassword(),userInfo.getUser().getPassword());
            if (isPasswordMatch){
                return userInfo.getUser();
            }
            else{
                return new PassNotMatchException("Incorrect password!");
            }
        }else{
            return new EmailIdNotFoundException("Email id not found");
        }
    }

    public ValidUserDto isUserPresent(String email){
        String emailId = email.toLowerCase(Locale.ROOT);
        User user = userRepo.findByEmail(emailId).orElse(new User());
        System.out.println(user.toString());
        if (user.getEmail() == null){
            return new ValidUserDto(false,user);
        }

        return new ValidUserDto(emailId.matches(user.getEmail()), user);
    }
}
