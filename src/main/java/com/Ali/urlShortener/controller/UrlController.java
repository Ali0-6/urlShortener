package com.Ali.urlShortener.controller;

import com.Ali.urlShortener.dto.CreateUrlRequest;
import com.Ali.urlShortener.dto.UrlResponse;
import com.Ali.urlShortener.model.Url;
import com.Ali.urlShortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public UrlResponse createShortUrl(@RequestBody CreateUrlRequest request) {
        Url savedUrl = urlService.createShortUrl(request.getOriginalUrl());

        String fullShortUrl = "http://localhost:8080/" + savedUrl.getShortCode();

        return new UrlResponse(
                savedUrl.getShortCode(),
                savedUrl.getOriginalUrl(),
                fullShortUrl
        );
    }

}