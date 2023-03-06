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
import java.util.List;


@RestController
public class UrlController {

    private final UrlService aUrlService;

    @Autowired
    public UrlController(UrlService pUrlService) {
        aUrlService = pUrlService;
    }


    @PostMapping("/UrlShortener")
    public String dispatch(@RequestParam(value = "URL") String pUrl) throws MalformedURLException, URISyntaxException {
        if(aUrlService.checkIfGenerated(pUrl)){return aUrlService.getLongUrl(pUrl);}
        if(!aUrlService.isValidURL(pUrl)){return "Invalid URL";}
        return convertToShort(pUrl);
    }

    private String convertToShort(String pLongUrl) throws MalformedURLException, URISyntaxException {
        return aUrlService.createShortUrl(pLongUrl);
    }

    @GetMapping(value = "{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl){
        try {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(aUrlService.getLongUrl(shortUrl)))
                    .build();
        } catch (NullPointerException e){
            System.out.println("1");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Page not found"
            );
        }
    }
}
