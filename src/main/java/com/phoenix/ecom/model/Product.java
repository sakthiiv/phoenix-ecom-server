package com.phoenix.ecom.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "product")
public class Product implements Serializable {
    String prodName;
    @Id
    String id;
    public String getName() {
        return prodName;
    }

    public void setName(String prodName) {
        this.prodName = prodName;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




}
