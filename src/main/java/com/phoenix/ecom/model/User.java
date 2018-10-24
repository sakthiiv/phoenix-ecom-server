package com.phoenix.ecom.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user")
public class User {

    @Id
    String id;
    String userName;
    String emailId;
    String role;
    String password;
    Boolean IsValid;

    public String getId(){return id;}

    public void setId(String id){this.id = id;}

    public void setUserName(String s) {
        this.userName = s;
    }

    public String getUserName(){
        return userName;
    }

    public void setEmailId(String s) {
        this.emailId = s;
    }

    public String getEmailId(){
        return emailId;
    }

    public void setRole(String s) {
        this.role = s;
    }

    public String getRole(){
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public Boolean getValid() {
        return IsValid;
    }

    public void setValid(Boolean valid) {
        IsValid=valid;
    }
}
