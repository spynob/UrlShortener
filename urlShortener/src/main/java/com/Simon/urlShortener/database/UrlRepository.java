package com.Simon.urlShortener.database;

import com.Simon.urlShortener.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


//No new methods are needed so instantiate simpleMongoRepository
@Repository("Database")
public interface UrlRepository extends MongoRepository<Url, String> {
}
