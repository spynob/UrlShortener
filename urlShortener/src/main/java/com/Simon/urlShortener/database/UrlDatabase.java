package com.Simon.urlShortener.database;

import com.Simon.urlShortener.model.Url;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("Database")
public class UrlDatabase {

    private final Map<String, String> DB = new HashMap<>(); //base de données temporaire

    public boolean contains(String pUrl){
        return DB.containsKey(pUrl);
    }

    public int addURL(Url pUrl){
        DB.putIfAbsent(pUrl.getShortUrl(), pUrl.getLongUrl());
        return 0;
    }

    public String getLongUrl(String pShortUrl){
        return DB.get(pShortUrl);
    }

}
