package com.phoenix.ecom.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "image")
public class Image {

    String id;
    String content;

    public Image(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
