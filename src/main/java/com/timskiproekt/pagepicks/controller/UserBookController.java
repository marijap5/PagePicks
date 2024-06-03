package com.timskiproekt.pagepicks.controller;

import com.timskiproekt.pagepicks.model.BookStatus;
import com.timskiproekt.pagepicks.model.UserBook;
import com.timskiproekt.pagepicks.service.UserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mylibrary")
public class UserBookController {
    private final UserBookService userBookService;

    @Autowired
    public UserBookController(UserBookService userBookService) {
        this.userBookService = userBookService;
    }

    @PostMapping("/add-to-read-list")
    public UserBook addToReadList(@RequestParam Long userId, @RequestParam String bookISBN) {
        return userBookService.addToReadList(userId, bookISBN);
    }

    @PostMapping("/add-to-already-read")
    public UserBook addToAlreadyRead(@RequestParam Long userId, @RequestParam String bookISBN) {
        return userBookService.addToAlreadyRead(userId, bookISBN);
    }

    @PostMapping("/add-to-in-progress")
    public UserBook addToInProgress(@RequestParam Long userId, @RequestParam String bookISBN) {
        return userBookService.addToInProgress(userId, bookISBN);
    }

    @PutMapping("/update-status")
    public void updateStatus(@RequestParam Long userId, @RequestParam BookStatus status) {
        userBookService.updateStatus(userId, status);
    }

    @GetMapping("/user-books")
    public List<UserBook> getUserBooksByStatus(@RequestParam Long userId, @RequestParam BookStatus status) {
        return userBookService.getUserBooksByStatus(userId, status);
    }

    @DeleteMapping("/remove-from-list")
    public void removeFromList(@RequestParam Long userId, @RequestParam String bookISBN) {
        userBookService.removeFromList(userId, bookISBN);
    }
}
