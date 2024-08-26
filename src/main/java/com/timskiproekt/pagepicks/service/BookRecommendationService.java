package com.timskiproekt.pagepicks.service;

import com.timskiproekt.pagepicks.model.Book;
import com.timskiproekt.pagepicks.model.BookStatus;
import com.timskiproekt.pagepicks.model.UserBookStatus;
import com.timskiproekt.pagepicks.repository.BookRepository;
import com.timskiproekt.pagepicks.repository.UserBookStatusRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookRecommendationService {
    private final UserBookStatusRepository userBookStatusRepository;
    private final BookRepository bookRepository;

    public BookRecommendationService(UserBookStatusRepository userBookStatusRepository, BookRepository bookRepository) {
        this.userBookStatusRepository = userBookStatusRepository;
        this.bookRepository = bookRepository;
    }

    public List<Book> getRecommendations(Long userId){
        List<UserBookStatus> userBookStatuses = userBookStatusRepository.findByUserId(userId);

        Set<String> favoriteGenres = userBookStatuses.stream()
                .filter(status -> status.getStatus() == BookStatus.READ || status.getStatus() == BookStatus.READING)
                .map(status -> status.getBook().getGenre())
                .collect(Collectors.toSet());

        Set<String> favoriteAuthors = userBookStatuses.stream()
                .filter(status -> status.getStatus() == BookStatus.READ || status.getStatus() == BookStatus.READING)
                .map(status -> status.getBook().getAuthor())
                .collect(Collectors.toSet());

        Set<Book> recommendedBooks = new HashSet<>();

        for (String genre : favoriteGenres) {
            recommendedBooks.addAll(bookRepository.findByGenre(genre));
        }

        for (String author : favoriteAuthors) {
            recommendedBooks.addAll(bookRepository.findByAuthor(author));
        }

        if (recommendedBooks.isEmpty()) {
            recommendedBooks.addAll(bookRepository.findTop10ByOrderByTitleAsc());
        }

        return new ArrayList<>(recommendedBooks);
    }
}
