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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        Url url = urlService.getAndTrackClick(shortCode);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, url.getOriginalUrl());

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @RestController
    public class RedirectController {

        private final UrlService urlService;

        @Autowired
        public RedirectController(UrlService urlService) {
            this.urlService = urlService;
        }

        @GetMapping("/{shortCode}")
        public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
            Url url = urlService.getAndTrackClick(shortCode);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.LOCATION, url.getOriginalUrl());

            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        }
    }

    @PostMapping
    public UrlResponse createShortUrl(@Valid @RequestBody CreateUrlRequest request) {
        Url savedUrl = urlService.createShortUrl(request.getOriginalUrl());

        String fullShortUrl = "http://localhost:8080/" + savedUrl.getShortCode();

        return new UrlResponse(
                savedUrl.getShortCode(),
                savedUrl.getOriginalUrl(),
                fullShortUrl
        );
    }



}