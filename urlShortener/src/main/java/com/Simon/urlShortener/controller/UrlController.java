package com.Simon.urlShortener.controller;

import com.Simon.urlShortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;

@RestController
public class UrlController {

    private final UrlService aUrlService;

    @Autowired
    public UrlController(UrlService pUrlService) {
        aUrlService = pUrlService;
    }

    //handles the inputted url
    //Original url -> checks if valid url (throws 400 bad request if not) and outputs shortened url (will check if already in database)
    //shortened url present in database -> outputs the original url
    @PostMapping("/UrlShortener")
    public String handler(@RequestParam(value = "URL") String pUrl) throws MalformedURLException, URISyntaxException {
        System.out.println("INPUT: " + pUrl);
        if(aUrlService.checkIfGenerated(pUrl)){
            System.out.println("Previously generated short url \nReturning corresponding long url ");
            String longUrl = aUrlService.getLongUrl(pUrl);
            System.out.println("Long url retrieved successfully");
            return longUrl;
        }
        if(!aUrlService.isValidUrl(pUrl)){
            System.out.println("Invalid url");
            return "Invalid URL\nMake sure the URL refers to a valid and that it does not contain any typos";
        }
        return convertToShort(pUrl);
    }

    //returns shortened url and adds it to the database
    //@pre url is not already in database and is not null
    private String convertToShort(String pLongUrl) throws MalformedURLException, URISyntaxException {
        System.out.println("Valid new long url sent to conversion");
        return aUrlService.convertToShort(pLongUrl);
    }

    //redirects user to the original page when short url is appended to path
    @GetMapping(value = "{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl){
        System.out.println("Redirecting to url corresponding to " + shortUrl + "...");
        try {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(aUrlService.getLongUrl(shortUrl)))
                    .build();
        } catch (NoSuchElementException e){
            System.out.println("Invalid short url: Page not found");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Page not found"
            );
        }
    }
}
