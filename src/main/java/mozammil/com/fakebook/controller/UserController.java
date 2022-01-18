package mozammil.com.fakebook.controller;

import mozammil.com.fakebook.dto.ValidUserDto;
import mozammil.com.fakebook.exception.EmailIdNotFoundException;
import mozammil.com.fakebook.exception.PassNotMatchException;
import mozammil.com.fakebook.model.User;
import mozammil.com.fakebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        Object object = userService.login(user);
        if (object instanceof EmailIdNotFoundException){
            return new ResponseEntity<String>(((EmailIdNotFoundException) object).getMessage(), HttpStatus.NOT_FOUND);
        }else if(object instanceof PassNotMatchException){
            return new ResponseEntity<String>(((PassNotMatchException) object).getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<User>((User) object, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<ValidUserDto> singUp(@RequestBody User user){
        ValidUserDto userInfo = userService.signUp(user);
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }
}
