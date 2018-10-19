package com.phoenix.ecom.repository.image;

import com.phoenix.ecom.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ImageRepository  implements  IImageRepository{

    @Autowired
    MongoTemplate mongoTemplate;

    public String getImageContent(String imageId) {
        Image imageReceived = mongoTemplate.findById(imageId, Image.class);
        return imageReceived.getContent();
    }

}
