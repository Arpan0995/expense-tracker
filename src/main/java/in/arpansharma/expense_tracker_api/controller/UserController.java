package in.arpansharma.expense_tracker_api.controller;

import in.arpansharma.expense_tracker_api.models.User;
import in.arpansharma.expense_tracker_api.models.UserModel;
import in.arpansharma.expense_tracker_api.service.UserService;
import in.arpansharma.expense_tracker_api.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User Register/Login")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody UserModel userModel){
        return userService.registerUser(userModel);
    }
}
