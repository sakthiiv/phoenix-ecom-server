package com.phoenix.ecom.service.user;

import com.phoenix.ecom.config.Authentication;
import com.phoenix.ecom.model.User;
import com.phoenix.ecom.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Authentication authentication;

    public LoginService(UserRepository userRepository,Authentication authentication) {
        this.userRepository = userRepository;
        this.authentication = authentication;
    }


    public Optional<Map<String,String>> login(User user) {
        User loggedInUser = userRepository.getUser(user.getUserName(),user.getPassword());
       if(loggedInUser != null) {
           Map<String,String> userDetails = new HashMap<String,String>();
           userDetails.put("role",loggedInUser.getRole());
           userDetails.put("userId",loggedInUser.getId());
           userDetails.put("token",authentication.createJWT(loggedInUser));
           userDetails.put("userName",loggedInUser.getUserName());
           return Optional.ofNullable(userDetails);
       }
       else
           return Optional.ofNullable(null);
    }


}
