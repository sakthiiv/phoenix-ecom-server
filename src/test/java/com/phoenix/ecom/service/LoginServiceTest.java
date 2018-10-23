package com.phoenix.ecom.service;


import com.phoenix.ecom.config.Authentication;
import com.phoenix.ecom.model.User;
import com.phoenix.ecom.repository.user.UserRepository;
import com.phoenix.ecom.service.user.LoginService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;

    private LoginService loginService;

    @Before
    public void setUp() throws Exception {
        loginService = new LoginService(userRepository,authentication);
    }


    @Test
    public void shouldLoginUser(){
        User user = new User();
        user.setEmailId("abc@abc.com");
        user.setRole("customer");

        when(userRepository.getUser(user.getUserName(),user.getPassword())).thenReturn(user);
        when(authentication.createJWT(user)).thenReturn("JWTToken");

        Assert.assertEquals(loginService.login(user),"JWTToken");
    }





}
