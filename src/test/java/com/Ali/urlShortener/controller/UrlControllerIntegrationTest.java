package com.Ali.urlShortener.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;


@SpringBootTest
@com.Ali.urlShortener.controller.AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
class UrlControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createShortUrl_thenRedirect_worksEndToEnd() throws Exception {
        String requestJson = """
                {
                    "originalUrl": "https://www.example.com"
                }
                """;

        mockMvc.perform(post("/api/urls")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.originalUrl").value("https://www.example.com"))
                .andExpect(jsonPath("$.shortCode").isNotEmpty());
    }

    @Test
    void createShortUrl_withInvalidUrl_returns400() throws Exception {
        String requestJson = """
                {
                    "originalUrl": "not-a-url"
                }
                """;

        mockMvc.perform(post("/api/urls")
                        .contentType("application/json")
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void redirect_withUnknownCode_returns404() throws Exception {
        mockMvc.perform(get("/doesnotexist"))
                .andExpect(status().isNotFound());
    }
}