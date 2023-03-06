package com.Simon.urlShortener.model;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Url { //pas trop sur quoi faire avec cette classe encore par rapport à spring boot

    @Id
    private final String id;

    private final String LongUrl;

    public Url(String LongUrl, String id) {
        this.LongUrl = LongUrl;
        this.id = id;

    }

    public String getShortUrl() {
        return id;
    }

    public String getLongUrl() {
        return LongUrl;
    }
}
