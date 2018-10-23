package com.phoenix.ecom.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoException;
import com.phoenix.ecom.model.Category;
import com.phoenix.ecom.model.User;
import com.phoenix.ecom.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp(){
        super.setUp();
    }

    @MockBean
    private UserService userService;


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
    public void shouldRegisterAUser() throws Exception{
        User user = new User();
        user.setUserName("user!");
        user.setEmailId("user@user.com");
        user.setRole("User");
        user.setPassword("Password");


        this.mvc.perform(post("/api/v1/user/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user))).andDo(print()).andExpect(status().isCreated())
                .andExpect(content().string(containsString("User created successfully")));

        ArgumentCaptor<User> ac = ArgumentCaptor.forClass(User.class);

        verify(userService, times(1)).createNewUser(ac.capture());
        User value = ac.getValue();
        assertEquals("User",value.getRole());

    }

    @Test
    public void shouldNotifyTheUserThatAParticularUserCredentialsAlreadyExistsWhileCreatingAUserWithTheSameUserNameOrEmail() throws Exception{
        User user = new User();
        user.setUserName("abc");
        user.setEmailId("abc@abc.com");

        ArgumentCaptor<User> ac = ArgumentCaptor.forClass(User.class);
        doThrow(new Exception("User already exists!")).when(userService).createNewUser(ac.capture());


        this.mvc.perform(post("/api/v1/user/register").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user))).andDo(print())
                .andExpect(status().isConflict()).andExpect(content().string(containsString("User Already Exists!")));

    }
}
