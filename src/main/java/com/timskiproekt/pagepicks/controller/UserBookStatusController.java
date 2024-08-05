package com.timskiproekt.pagepicks.controller;

import com.timskiproekt.pagepicks.model.UserBookStatus;
import com.timskiproekt.pagepicks.service.UserBookStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-book-status")
public class UserBookStatusController {

    @Autowired
    private UserBookStatusService userBookStatusService;

    @GetMapping("/user/{userId}")
    public List<UserBookStatus> getUserBookStatusesByUserId(@PathVariable Long userId) {
        return userBookStatusService.getUserBookStatusesByUserId(userId);
    }

    @PostMapping("/save")
    public UserBookStatus saveUserBookStatus(@RequestBody UserBookStatus userBookStatus) {
        return userBookStatusService.saveUserBookStatus(userBookStatus);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserBookStatus(@PathVariable Long id) {
        userBookStatusService.deleteUserBookStatus(id);
    }



}
