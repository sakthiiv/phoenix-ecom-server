package com.phoenix.ecom.service;

import com.phoenix.ecom.model.Category;
import com.phoenix.ecom.model.User;
import com.phoenix.ecom.repository.user.AdminRepository;
import com.phoenix.ecom.repository.user.CustomerRepository;
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

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AdminRepository adminRepository;

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserService(userRepository,customerRepository,adminRepository);
    }
    @Test
    public void shouldCreateANewUser() throws Exception {
        User user = new User();
        user.setEmailId("abc@abc.com");
        user.setRole("customer");

        ArgumentCaptor<User> ac = ArgumentCaptor.forClass(User.class);
        userService.createNewUser(user);

        verify(userRepository, times(1)).SaveUser(user);

    }

//    @Test
//    public void shouldNotCreateAUserIfTheUserNameOrEmailIdAlreadyExists() throws Exception{
//        User user = new User();
//        user.setEmailId("abc@abc.com");
//        //user.setUserName();
//    }

}
