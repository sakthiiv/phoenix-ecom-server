package com.phoenix.ecom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.ecom.model.User;
import com.phoenix.ecom.service.user.LoginService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginControllerTest extends AbstractTest{


    @MockBean
    private LoginService loginService;

    @Before
    public void setUp(){
        super.setUp();
    }


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper=new ObjectMapper();
            final String jsonContent=mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    public void shouldForbidLoginOfUser() throws Exception{

        String username = "user!";
        String password = "123456";
        User user = new User();
        user.setUserName("user!");
        user.setRole("Admin");
        user.setId("123456");
        user.setPassword("Password");
        Map<String,String> userDetails = new HashMap<String,String>();
        userDetails.put("token","JWTToken");
        userDetails.put("role","admin");
        userDetails.put("userId","123456");
        Optional<Map<String,String>> loggedInUser =  Optional.of(userDetails);

        when(loginService.login(user)).thenReturn(loggedInUser);

        this.mvc.perform(post("/api/v1/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andDo(print()).andExpect(status().isForbidden());
    }

}
