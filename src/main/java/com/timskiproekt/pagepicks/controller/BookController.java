package com.timskiproekt.pagepicks.controller;

import com.timskiproekt.pagepicks.domain.model.dto.BookDTO;
import com.timskiproekt.pagepicks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/isbn")
    public Optional<BookDTO> getBookByISBN(@PathVariable String ISBN) {
        return bookService.getBookByISBN(ISBN);
    }

    @PostMapping("/save")
    public BookDTO saveBook(@RequestBody BookDTO book) {
        return bookService.saveBook(book);
    }

    @DeleteMapping("/delete/{ISBN}")
    public void deleteBook(@PathVariable String ISBN) {
        bookService.deleteBook(ISBN);
    }

    @GetMapping(value = "/search", produces = "application/json")
    public List<BookDTO> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String author) {
        return bookService.searchBooks(title, genre, author);
    }
}
