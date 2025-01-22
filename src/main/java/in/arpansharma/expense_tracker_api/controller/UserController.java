package in.arpansharma.expense_tracker_api.controller;

import in.arpansharma.expense_tracker_api.models.User;
import in.arpansharma.expense_tracker_api.models.UserModel;
import in.arpansharma.expense_tracker_api.service.UserService;
import in.arpansharma.expense_tracker_api.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Register/Login")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserModel userModel){
        return new ResponseEntity<User>(userService.registerUser(userModel),HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> readUser(@PathVariable Long id){
        return new ResponseEntity<User>(userService.readUser(id),HttpStatus.OK);
    }
}
