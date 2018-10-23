package com.phoenix.ecom.repository.user;

import com.phoenix.ecom.model.User;

public interface IUserRepository {

    void SaveUser(User user);
    boolean VerifyUserAlreadyExists(User user);
    User getUser(String userName,String password);

}
