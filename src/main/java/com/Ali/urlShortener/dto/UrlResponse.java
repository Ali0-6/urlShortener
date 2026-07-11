package com.Ali.urlShortener.dto;

public class UrlResponse {

    private String shortCode;
    private String originalUrl;
    private String shortUrl;

    public UrlResponse(String shortCode, String originalUrl, String shortUrl) {
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }
}