package com.Simon.urlShortener.service;

import com.Simon.urlShortener.database.UrlRepository;
import com.Simon.urlShortener.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

@Service
public class UrlService {

    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    private UrlRepository repository;
    public final char[] base62 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y','z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K' ,'L' ,'M', 'N' ,'O', 'P' ,'Q', 'R' , 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    @Autowired
    public UrlService(@Qualifier("Database") UrlRepository pUrlDatabase) {
        repository = pUrlDatabase;

    }

    private int addURL(Url pUrl){
        repository.insert(pUrl);
        return 0;
    }

    public boolean checkIfGenerated(String pUrl){
        return repository.findById(pUrl).isPresent();
    }

    public boolean isValidURL(String url) throws MalformedURLException, URISyntaxException {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }


    public String getLongUrl(String pShortUrl){
        Optional<Url> url = repository.findById(pShortUrl);
        return url.get().getLongUrl();
    }
    public String createShortUrl(String pLongUrl) {
        int hash = pLongUrl.hashCode();
        if (hash < 0){
            hash = hash * -1;
        }
        String shortUrl = toBase62(hash);
        if(!checkIfGenerated(shortUrl)){addURL(new Url(pLongUrl, shortUrl));}
        return shortUrl;
    }

    private String toBase62(int num){
        String shortUrl = "";
        int remainder;
        while (num > 0){
            remainder = num % 62;
            num = num / 62;
            shortUrl = base62[remainder] + shortUrl;
        }
        return shortUrl;
    }
}
