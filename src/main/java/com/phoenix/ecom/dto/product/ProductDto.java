package com.phoenix.ecom.dto.product;

public class ProductDto {

    public ProductDto(String name, float price, String categoryId, String subCategoryId, String description, boolean isValid) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.description = description;
        this.isValid = isValid;
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

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    String name;

    float price;

    String categoryId;

    String subCategoryId;

    String description;

    boolean isValid;

}
