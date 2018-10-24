package com.phoenix.ecom.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;



@Document(collection = "category")
public class Category implements Serializable {

    @Id
    String id;
    String description;
    String name;
    Boolean IsValid;
    List<Category> subCategory;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public Boolean getValid() {
        return IsValid;
    }

    public void setValid(Boolean valid) {
        IsValid=valid;
    }

    public List<Category> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(List<Category> subCategory) {
        this.subCategory=subCategory;
    }
}
