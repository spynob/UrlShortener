package com.Simon.urlShortener.model;

public class Url { //pas trop sur quoi faire avec cette classe encore
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
