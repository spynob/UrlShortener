package com.Simon.urlShortener.service;

import com.Simon.urlShortener.database.UrlRepository;
import com.Simon.urlShortener.model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

@Service
public class UrlService {

    @Autowired
    private UrlRepository repository;

    //not the prettiest, but fast
    public final char[] base62 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y','z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K' ,'L' ,'M', 'N' ,'O', 'P' ,'Q', 'R' , 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    @Autowired
    public UrlService(@Qualifier("Database") UrlRepository pUrlDatabase) {
        repository = pUrlDatabase;

    }

    //adds url object to database
    private void addURL(Url pUrl){
        repository.insert(pUrl);
    }

    //checks if short url is already in database
    public boolean checkIfGenerated(String pUrl){
        return repository.findById(pUrl).isPresent();
    }

    //Checks if url is valid
    public boolean isValidURL(String url) throws MalformedURLException, URISyntaxException {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }

    //retrieves the original url when shortened url is inputted
    public String getLongUrl(String pShortUrl){
        Optional<Url> url = repository.findById(pShortUrl);
        return url.get().getLongUrl();
    }

    //creates the shortened url and adds it to the database if not present yet
    //handles collisions: if two urls have the same hashcode, will try new urls until the url
    // is unique in the database
    public String createShortUrl(String pLongUrl) {
        int hash = pLongUrl.hashCode();
        if (hash < 0){
            hash = hash * -1;
        }
        String shortUrl = toBase62(hash);
        if(!checkIfGenerated(shortUrl)){
            addURL(new Url(pLongUrl, shortUrl));
        } else if (repository.findById(shortUrl).get().getLongUrl() != pLongUrl) {
            int collisionHandler = 0;
            while (checkIfGenerated(shortUrl)){
                shortUrl += base62[collisionHandler % 62];
                collisionHandler++;
            }
        }
        return shortUrl;
    }

    //helper method to convert hash into base62 int
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
