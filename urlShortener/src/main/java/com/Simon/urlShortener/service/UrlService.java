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
    private final char[] base62 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y','z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K' ,'L' ,'M', 'N' ,'O', 'P' ,'Q', 'R' , 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    @Autowired
    public UrlService(@Qualifier("Database") UrlRepository pUrlDatabase) {
        repository = pUrlDatabase;

    }

    //adds url object to database
    private void addUrl(Url pUrl){
        repository.insert(pUrl);
        System.out.println("New url inserted in database");
    }

    //checks if short url is already in database
    public boolean checkIfGenerated(String pShortUrl){
        System.out.println("Checking if " + pShortUrl + " is in database as short url...");
        return repository.findById(pShortUrl).isPresent();
    }

    //Checks if url is valid
    public boolean isValidUrl(String url) throws MalformedURLException, URISyntaxException {
        System.out.println("Checking if " + url + " is valid...");
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }

    //retrieves the original url when shortened url is inputted
    public String getLongUrl(String pShortUrl){
        System.out.println("Fetching long url corresponding to " + pShortUrl + "...");
        Optional<Url> url = repository.findById(pShortUrl);
        return url.get().getLongUrl();
    }

    //creates the shortened url and adds it to the database if not present yet
    //handles collisions: if two urls have the same hashcode, will try new urls until the url
    // is unique in the database
    public String convertToShort(String pLongUrl) {
        System.out.println("Requesting short url for " + pLongUrl + "...");
        int hash = pLongUrl.hashCode();
        if (hash < 0){
            hash = hash * -1;
        }
        String shortUrl = toBase62(hash);
        if (!checkIfGenerated(shortUrl)){
            System.out.println("Creating new short url for " + pLongUrl + "...");
            addUrl(new Url(pLongUrl, shortUrl));
        } else if (!repository.findById(shortUrl).get().getLongUrl().equals(pLongUrl)) {
            System.out.println("Collision encountered... Creating new short url");
            int collisionHandler = 0;
            while (checkIfGenerated(shortUrl)){
                shortUrl += base62[collisionHandler % 62];
                collisionHandler++;
            }
        }
        System.out.println("Short url request successfully!\nLong url: " + pLongUrl + "\nShort url: " + shortUrl);
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
