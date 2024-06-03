package com.timskiproekt.pagepicks.model;

import java.util.List;

public class Book {
    private String title;
    private List<String> authors;
    private String coverUrl;
    private String isbn;

    public Book(String title, List<String> authors, String coverUrl, String isbn) {
        this.title = title;
        this.authors = authors;
        this.coverUrl = coverUrl;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
