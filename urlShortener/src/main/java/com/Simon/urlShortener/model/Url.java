package com.Simon.urlShortener.model;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Url {

    @Id
    private final String id;

    private final String longUrl;

    public Url(String longUrl, String id) {
        this.longUrl = longUrl;
        this.id = id;

    }

    public String getShortUrl() {
        return id;
    }

    public String getLongUrl() {
        return longUrl;
    }
}
