package com.Simon.urlShortener.database;

import com.Simon.urlShortener.model.Url;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("Database")
public interface UrlRepository extends MongoRepository<Url, String> {
}
