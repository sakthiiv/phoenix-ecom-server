package com.phoenix.ecom.repository.image;

import com.phoenix.ecom.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ImageRepository  implements  IImageRepository{

    @Autowired
    MongoTemplate mongoTemplate;

    public String getImageContent(String imageId) {
        Image imageReceived = mongoTemplate.findOne(new Query(Criteria.where("imageId").is(imageId)), Image.class);
        return imageReceived.getImageContent();
    }

}
