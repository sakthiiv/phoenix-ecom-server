package com.phoenix.ecom.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
public class Customer {
    @Id
    String id;
    String userName;
    String emailId;
    String role;


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
}
