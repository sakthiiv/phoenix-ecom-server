package com.phoenix.ecom.controller;

import com.phoenix.ecom.model.Category;
import com.phoenix.ecom.model.User;
import com.phoenix.ecom.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<String> createNewUser(@RequestBody User user) {
        try {
            userService.createNewUser(user);
            return new ResponseEntity("{ \"message\":\"User created successfully\"}", HttpStatus.CREATED);
        } catch (Exception e) {
            if (e.getMessage() == "User already exists!") {
                return new ResponseEntity("{\"message\" : \"User Already Exists!\"}", HttpStatus.CONFLICT);
            }
            return new ResponseEntity("{\"message\" : \"Server Error\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
