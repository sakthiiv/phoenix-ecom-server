package com.phoenix.ecom.controller;

import com.phoenix.ecom.model.User;
import com.phoenix.ecom.service.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class LoginController {

    @Autowired
    private LoginService loginService;



    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    Map<String,String> loginUser(@RequestBody User user) {
        try {
            String token = loginService.login(user);
            HashMap<String,String> entity = new HashMap<>();
            entity.put("_token", token);
            return entity;
        } catch (Exception e) {
            HashMap<String,String> entity = new HashMap<>();
            entity.put("message", e.getMessage());
            return entity;
        }

    }
}
