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

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{isbn}")
    public Optional<BookDTO> getBookByISBN(@PathVariable String isbn) {
        return bookService.getBookByISBN(isbn);
    }

    @PostMapping("/save")
    public BookDTO saveBook(@RequestBody BookDTO book) {
        return bookService.saveBook(book);
    }

    @DeleteMapping("/delete/{isbn}")
    public void deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
    }

    @GetMapping(value = "/search", produces = "application/json")
    public List<BookDTO> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String author) {
        return bookService.searchBooks(title, genre, author);
    }
}
