package com.arpansharma.expense_tracker_api.controller;

import com.arpansharma.expense_tracker_api.models.User;
import com.arpansharma.expense_tracker_api.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
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


    @GetMapping("/user")
    public ResponseEntity<User> readUser(){
        return new ResponseEntity<User>(userService.readUser(),HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return new ResponseEntity<User>(userService.updateUser(user),HttpStatus.OK);
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<HttpStatus> deleteUser(){
        userService.deleteUser();
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
