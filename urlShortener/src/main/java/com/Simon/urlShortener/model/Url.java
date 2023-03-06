package com.Simon.urlShortener.model;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public class Url {
    private final String aShortUrl;
    private final String aLongUrl;

    public Url(String pLongUrl, String pShortUrl) {
        aLongUrl = pLongUrl;
        aShortUrl = pShortUrl;

    }

    public String getShortUrl() {
        return aShortUrl;
    }

    public String getLongUrl() {
        return aLongUrl;
    }
}
