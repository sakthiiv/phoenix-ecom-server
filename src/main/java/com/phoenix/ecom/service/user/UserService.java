package com.phoenix.ecom.service.user;

import com.phoenix.ecom.model.Admin;
import com.phoenix.ecom.model.Customer;
import com.phoenix.ecom.model.User;

import com.phoenix.ecom.repository.user.IAdminRepository;
import com.phoenix.ecom.repository.user.ICustomerRepository;
import com.phoenix.ecom.repository.user.IUserRepository;
import com.phoenix.ecom.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IAdminRepository adminRepository;

    public UserService(UserRepository userRepository,ICustomerRepository customerRepository,IAdminRepository adminRepository){
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.adminRepository = adminRepository;
    }
    public void createNewUser(User user){
        userRepository.SaveUser(user);
        if(user.getRole() == "customer"){
            CreateCustomer(user);
        }else {
            CreateAdmin(user);
        }

    }

    private void CreateCustomer(User user){
        Customer customer = new Customer();
        customer.setEmailId(user.getEmailId());
        customer.setRole(user.getRole());
        customer.setUserName(user.getUserName());
        this.customerRepository.SaveUser(customer);

    }

    private void CreateAdmin(User user){
        Admin admin = new Admin();
        admin.setEmailId(user.getEmailId());
        admin.setRole(user.getRole());
        admin.setUserName(user.getUserName());
        this.adminRepository.SaveUser(admin);
    }
}
