package com.arpansharma.expense_tracker_api.service;

import com.arpansharma.expense_tracker_api.models.User;
import com.arpansharma.expense_tracker_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(userRepository.existsByemail(username)){
            User user = userRepository.findByEmail(username);
            return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), Arrays.asList());
        }else{
            throw new UsernameNotFoundException("Username not found!");
        }
    }
}
