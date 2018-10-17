package com.phoenix.ecom.model;

import org.springframework.data.annotation.Id;

public class Product {
    String name;
    @Id
    String id;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




}
