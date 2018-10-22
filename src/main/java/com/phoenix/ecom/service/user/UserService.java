package com.phoenix.ecom.service.user;

import com.phoenix.ecom.model.User;

import com.phoenix.ecom.repository.user.IUserRepository;
import com.phoenix.ecom.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private IUserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public void createNewUser(User user){
        userRepository.SaveUser(user);

    }
}
