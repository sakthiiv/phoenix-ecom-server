package com.phoenix.ecom.repository.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ImageRepository  implements  IImageRepository{

    @Autowired
    MongoTemplate mongoTemplate;

    public String getImage(String imageId) {
        return mongoTemplate.findById(imageId, String.class, "image");
    }

}
