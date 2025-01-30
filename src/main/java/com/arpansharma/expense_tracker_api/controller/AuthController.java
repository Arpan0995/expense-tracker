package com.arpansharma.expense_tracker_api.controller;

import com.arpansharma.expense_tracker_api.io.JwtResponse;
import com.arpansharma.expense_tracker_api.models.LoginReq;
import com.arpansharma.expense_tracker_api.models.User;
import com.arpansharma.expense_tracker_api.models.UserModel;
import com.arpansharma.expense_tracker_api.service.CustomerDetailsService;
import com.arpansharma.expense_tracker_api.service.UserService;
import com.arpansharma.expense_tracker_api.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomerDetailsService customerDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginReq loginReq) throws Exception{
        authenticate(loginReq.getEmail(),loginReq.getPassword());

        UserDetails userDetails = customerDetailsService.loadUserByUsername(loginReq.getEmail());
        String token = jwtUtil.generateToken(userDetails);
        return new ResponseEntity<JwtResponse>(new JwtResponse(token),HttpStatus.OK);
    }

    private void authenticate(String email, String password) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        } catch (BadCredentialsException e) {
            throw new Exception("Bad Credentials");
        }
    }


    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserModel userModel){
        return new ResponseEntity<User>(userService.registerUser(userModel),HttpStatus.CREATED);
    }

}
