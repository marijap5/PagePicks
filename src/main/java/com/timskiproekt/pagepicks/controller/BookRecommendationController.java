package com.timskiproekt.pagepicks.controller;

import com.timskiproekt.pagepicks.domain.model.Book;
import com.timskiproekt.pagepicks.domain.model.User;
import com.timskiproekt.pagepicks.service.BookRecommendationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recommendation")
public class BookRecommendationController {

    private final BookRecommendationService bookRecommendationService;

    public BookRecommendationController(BookRecommendationService bookRecommendationService) {
        this.bookRecommendationService = bookRecommendationService;
    }

    @GetMapping
    public List<Book> getRecommendations(@AuthenticationPrincipal User user) {
        Long userId = user.getId();
        return bookRecommendationService.getRecommendations(userId);
    }

}
