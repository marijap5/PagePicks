package com.timskiproekt.pagepicks.controller;

import com.timskiproekt.pagepicks.model.Book;
import com.timskiproekt.pagepicks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/top")
    public List<Book> getTopBooksByCategory() {
        return bookService.getTopBooksByGenres();
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(name = "q") String query) {
        return bookService.searchBooks(query);
    }
}