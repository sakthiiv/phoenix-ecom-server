package com.phoenix.ecom.config.mongo;


import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;

public class MongoConfig {

    public static MongoDatabase getDbConnection(){
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoCredential credential = MongoCredential.createCredential("root", "phoenix",
                "root".toCharArray());
        MongoDatabase database = mongo.getDatabase("phoenix");
        return database;
    }
}
