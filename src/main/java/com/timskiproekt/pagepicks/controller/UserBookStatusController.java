package com.timskiproekt.pagepicks.controller;

import com.timskiproekt.pagepicks.domain.model.UserBookStatus;
import com.timskiproekt.pagepicks.service.UserBookStatusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-book-status")
@CrossOrigin(origins = "*")
public class UserBookStatusController {

    private final UserBookStatusService userBookStatusService;

    public UserBookStatusController(UserBookStatusService userBookStatusService) {
        this.userBookStatusService = userBookStatusService;
    }

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

    @PatchMapping("/updatePage/{id}")
    public UserBookStatus updateCurrentPage(@PathVariable Long id, @RequestBody Integer newPage) {
        return userBookStatusService.updateCurrentPage(id, newPage);
    }

    @PatchMapping("/updateRating/{id}")
    public UserBookStatus updateRating(@PathVariable Long id, @RequestBody Integer rating) {
        return userBookStatusService.updateRating(id, rating);
    }

    @PatchMapping("/updateReview/{id}")
    public UserBookStatus updateReview(@PathVariable Long id, @RequestBody String review) {
        return userBookStatusService.updateReview(id, review);
    }

    @PatchMapping("/updateFavorite/{id}")
    public UserBookStatus updateFavorite(@PathVariable Long id, @RequestBody boolean favorite) {
        return userBookStatusService.updateFavorite(id, favorite);
    }

}
