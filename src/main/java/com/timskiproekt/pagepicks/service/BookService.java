package com.timskiproekt.pagepicks.service;

import com.timskiproekt.pagepicks.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final RestTemplate restTemplate;

    public BookService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Book> getTopBooksByGenres() {
        List<String> genres = Arrays.asList("science_fiction", "fantasy", "romance", "mystery", "thriller",
                "horror", "adventure", "historical_fiction", "biography", "self_help");

        List<Book> topBooks = new ArrayList<>();
        for (String genre : genres) {
            topBooks.addAll(getBooksByCategory(genre));
        }
        return topBooks;
    }

    public List<Book> getBooksByCategory(String category) {
        String url = "https://openlibrary.org/subjects/" + category + ".json?limit=10";
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        List<Map<String, Object>> works = (List<Map<String, Object>>) response.get("works");
        List<Book> books = new ArrayList<>();

        for (int i = 0; i < Math.min(10, works.size()); i++) {
            Map<String, Object> work = works.get(i);
            if (work.containsKey("key")) {
                String workKey = (String) work.get("key");
                String workUrl = "https://openlibrary.org" + workKey + ".json";
                Map<String, Object> workDetails = restTemplate.getForObject(workUrl, Map.class);
                if (workDetails != null && workDetails.containsKey("editions")) {
                    List<String> editions = (List<String>) workDetails.get("editions");
                    if (!editions.isEmpty()) {
                        String editionKey = editions.get(0); // Assuming the first edition
                        String editionUrl = "https://openlibrary.org" + editionKey + ".json";
                        Map<String, Object> editionDetails = restTemplate.getForObject(editionUrl, Map.class);
                        if (editionDetails != null && editionDetails.containsKey("isbn")) {
                            List<String> isbns = (List<String>) editionDetails.get("isbn");
                            if (!isbns.isEmpty()) {
                                String title = (String) editionDetails.get("title");
                                List<Map<String, String>> authorMaps = (List<Map<String, String>>) editionDetails.get("authors");
                                List<String> authors = authorMaps.stream().map(author -> author.get("name")).collect(Collectors.toList());
                                String coverUrl = null; // Extract cover URL if available
                                String isbn = isbns.get(0); // Taking the first ISBN
                                books.add(new Book(title, authors, coverUrl, isbn));
                            }
                        }
                    }
                }
            }
        }
        return books;
    }


    public List<Book> searchBooks(String query) {
        String url = "https://openlibrary.org/search.json?q=" + query + "&limit=10";
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        List<Map<String, Object>> docs = (List<Map<String, Object>>) response.get("docs");
        List<Book> books = new ArrayList<>();

        for (int i = 0; i < Math.min(10, docs.size()); i++) {
            Map<String, Object> doc = docs.get(i);
            if (doc.containsKey("isbn") && doc.get("isbn") instanceof List) {
                String title = (String) doc.get("title");

                // Extract authors
                List<String> authors = (List<String>) doc.get("author_name");

                // Extract cover URL
                String coverUrl = null;
                if (doc.containsKey("cover_i")) {
                    coverUrl = "https://covers.openlibrary.org/b/id/" + doc.get("cover_i") + "-L.jpg";
                }

                // Extract ISBN
                String isbn = (String) ((List<?>) doc.get("isbn")).get(0);
                books.add(new Book(title, authors, coverUrl, isbn));
            }
        }
        return books;
    }

    public String getBookDetailsByISBN(String isbn) {
        String url = "https://openlibrary.org/api/books?bibkeys=ISBN:" + isbn + "&format=json&jscmd=data";
        return restTemplate.getForObject(url, String.class);
    }
}