package com.phoenix.ecom.repository.user;


import com.phoenix.ecom.model.Customer;

public interface ICustomerRepository {
    void SaveUser(Customer customer);
}
