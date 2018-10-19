package com.phoenix.ecom.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "image")
public class Image {

    String imageId;
    String imageContent;

    public Image(String imageId, String imageContent) {
        this.imageId = imageId;
        this.imageContent = imageContent;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageContent() {
        return imageContent;
    }

    public void setImageContent(String imageContent) {
        this.imageContent = imageContent;
    }

}
