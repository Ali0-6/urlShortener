package com.Ali.urlShortener.service;

import com.Ali.urlShortener.model.Url;
import com.Ali.urlShortener.repository.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UrlServiceTest {

    @Mock
    private UrlRepository urlRepository;

    @InjectMocks
    private UrlService urlService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createShortUrl_savesAndReturnsUrl() {
        when(urlRepository.findByShortCode(anyString())).thenReturn(Optional.empty());
        when(urlRepository.save(any(Url.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Url result = urlService.createShortUrl("https://example.com");

        assertNotNull(result.getShortCode());
        assertEquals(6, result.getShortCode().length());
        assertEquals("https://example.com", result.getOriginalUrl());
        verify(urlRepository, times(1)).save(any(Url.class));
    }

    @Test
    void createShortUrl_retriesOnCollision() {
        when(urlRepository.findByShortCode(anyString()))
                .thenReturn(Optional.of(new Url("existing", "https://taken.com")))
                .thenReturn(Optional.empty());
        when(urlRepository.save(any(Url.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Url result = urlService.createShortUrl("https://example.com");

        assertNotNull(result);
        verify(urlRepository, times(2)).findByShortCode(anyString());
    }
}