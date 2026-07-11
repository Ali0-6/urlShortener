package com.Ali.urlShortener.service;

import com.Ali.urlShortener.model.Url;
import com.Ali.urlShortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int SHORT_CODE_LENGTH = 6;
    private final SecureRandom random = new SecureRandom();

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Url createShortUrl(String originalUrl) {
        String shortCode = generateUniqueShortCode();
        Url url = new Url(shortCode, originalUrl);
        return urlRepository.save(url);
    }

    private String generateUniqueShortCode() {
        String code;
        do {
            code = generateRandomCode();
        } while (urlRepository.findByShortCode(code).isPresent());
        return code;
    }

    private String generateRandomCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            int index = random.nextInt(ALLOWED_CHARACTERS.length());
            sb.append(ALLOWED_CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

}