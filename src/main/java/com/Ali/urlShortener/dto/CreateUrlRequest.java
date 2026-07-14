package com.Ali.urlShortener.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateUrlRequest {

    @NotBlank(message = "originalUrl must not be empty")
    @Pattern(regexp = "^https?://.+", message = "originalUrl must start with http:// or https://")


    private String originalUrl;

    public CreateUrlRequest() {
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}