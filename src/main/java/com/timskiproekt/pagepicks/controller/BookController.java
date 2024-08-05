package com.timskiproekt.pagepicks.controller;

import com.timskiproekt.pagepicks.model.Book;
import com.timskiproekt.pagepicks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/isbn")
    public Optional<Book> getBookByISBN(@PathVariable String ISBN) {
        return bookService.getBookByISBN(ISBN);
    }

    @PostMapping("/save")
    public Book saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @DeleteMapping("/delete/{ISBN}")
    public void deleteBook(@PathVariable String ISBN) {
        bookService.deleteBook(ISBN);
    }

}
