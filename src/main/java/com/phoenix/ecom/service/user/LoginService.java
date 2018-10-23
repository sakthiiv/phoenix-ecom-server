package com.phoenix.ecom.service.user;

import com.phoenix.ecom.config.Authentication;
import com.phoenix.ecom.model.User;
import com.phoenix.ecom.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public String login(User user) {
        User loggedInUser = userRepository.getUser(user.getUserName(),user.getPassword());
       if(loggedInUser != null) {
           return authentication.createJWT(loggedInUser);
       }
       else
           return null;
    }


}
