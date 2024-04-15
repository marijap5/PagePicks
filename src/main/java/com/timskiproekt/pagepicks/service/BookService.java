package com.timskiproekt.pagepicks.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookService {
    private static final String OPEN_LIBRARY_API_URL = "https://openlibrary.org/api/books";

    private final RestTemplate restTemplate;

    public BookService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public String getBookDetailsByISBN(String isbn) {
        String apiUrl = OPEN_LIBRARY_API_URL + "?bibkeys=ISBN" + isbn + "&format=json";
        return restTemplate.getForObject(apiUrl, String.class);
    }
}
