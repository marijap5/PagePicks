package com.timskiproekt.pagepicks.controller;

import com.timskiproekt.pagepicks.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String getBookDetailsByISBN(@RequestParam String isbn) {
        return bookService.getBookDetailsByISBN(isbn);
    }
}
