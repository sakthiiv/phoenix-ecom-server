package com.phoenix.ecom.service;

import com.phoenix.ecom.model.Category;
import com.phoenix.ecom.model.User;
import com.phoenix.ecom.repository.user.UserRepository;
import com.phoenix.ecom.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserService(userRepository);
    }
    @Test
    public void shouldDeleteASpecificCategory() {
        User user = new User();
        user.setEmailId("abc@abc.com");
        ArgumentCaptor<User> ac = ArgumentCaptor.forClass(User.class);
        userService.createNewUser(user);

        verify(userRepository, times(1)).SaveUser(user);

    }
}
