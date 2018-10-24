package com.phoenix.ecom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "product")
public class Product implements Serializable {

    Product(){
    }

    public Product(String name, float price, String categoryId, String subCategoryId, String description, String imageContent) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.description = description;
        this.imageContent = imageContent;
    }

    @Id
    String id;

    String name;

    float price;

    String categoryId;

    String subCategoryId;

    String description;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String imageContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageContent() {
        return imageContent;
    }

    public void setImageContent(String imageContent) {
        this.imageContent = imageContent;
    }

}
