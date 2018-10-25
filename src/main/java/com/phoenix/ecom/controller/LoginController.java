package com.phoenix.ecom.controller;

import com.phoenix.ecom.model.User;
import com.phoenix.ecom.service.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class LoginController {

    @Autowired
    private LoginService loginService;



    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> loginUser(@RequestBody User user) {
        try {
            Optional<Map<String,String>> userDetails = loginService.login(user);
            if(userDetails.isPresent()) {
                return new ResponseEntity<>
                        ("{\"token\":\"" + userDetails.get().get("token")+ "\",\"role\":\""+ userDetails.get().get("role") +
                                "\",\"userId\":\""+ userDetails.get().get("userId") + "\",\"userName\":\""+ userDetails.get().get("userName")  + "\"}", HttpStatus.OK);
            }else{
                return new ResponseEntity("{\"message\":\"Login Failed\"}", HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return new ResponseEntity("{\"message\":\"Server Error\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        try{
            request.logout();
        }catch (Exception e){

        }
        return "redirect:/";
    }
}
