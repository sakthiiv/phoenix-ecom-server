package com.phoenix.ecom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.ecom.model.User;
import com.phoenix.ecom.service.user.LoginService;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.HashMap;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    public void shouldLoginUser() throws Exception{

        String username = "user!";
        String password = "123456";
        User user = new User();
        user.setUserName("user!");
        user.setEmailId("user@user.com");
        user.setRole("User");
        user.setPassword("Password");
        HashMap<String,String> entity = new HashMap<>();
        entity.put("_token", "JWTToken");

        when(loginService.login(user)).thenReturn("JWTToken");

        this.mvc.perform(post("/api/v1/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andDo(print()).andExpect(status().isOk());
    }


}
