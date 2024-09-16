package com.timskiproekt.pagepicks.service;

import com.timskiproekt.pagepicks.domain.model.BookStatus;
import com.timskiproekt.pagepicks.domain.model.UserBookStatus;
import com.timskiproekt.pagepicks.domain.model.dto.RatingReviewDTO;
import com.timskiproekt.pagepicks.repository.UserBookStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserBookStatusService {

    private final UserBookStatusRepository userBookStatusRepository;

    @Autowired
    public UserBookStatusService(UserBookStatusRepository userBookStatusRepository) {
        this.userBookStatusRepository = userBookStatusRepository;
    }

    public List<UserBookStatus> getUserBookStatusesByUserId(Long userId) {
        return userBookStatusRepository.findByUserId(userId);
    }

    public UserBookStatus saveUserBookStatus(UserBookStatus userBookStatus) {
        Optional<UserBookStatus> existingStatus = userBookStatusRepository.findByUserIdAndBookIsbn(userBookStatus.getUser().getId(), userBookStatus.getBook().getIsbn());
        if (existingStatus.isPresent()) {
            UserBookStatus existingUserBookStatus = existingStatus.get();
            if (existingStatus.get().getStatus() == BookStatus.READING && userBookStatus.getStatus() == BookStatus.READING) {
                throw new IllegalArgumentException("User is already reading this book");
            }
            if (existingStatus.get().getStatus() == BookStatus.READ && userBookStatus.getStatus() == BookStatus.READ) {
                throw new IllegalArgumentException("User has already read this book");
            }
            if (existingStatus.get().getStatus() == BookStatus.TO_READ && userBookStatus.getStatus() == BookStatus.TO_READ) {
                throw new IllegalArgumentException("User already has this book on their to-read list");
            }
            if (existingStatus.get().getStatus() != BookStatus.READING && userBookStatus.getStatus() == BookStatus.READING) {
                existingUserBookStatus.setCurrentPage(0);
            }
            existingUserBookStatus.setStatus(userBookStatus.getStatus());
            return userBookStatusRepository.save(existingUserBookStatus);
        } else {
            if (userBookStatus.getStatus() == BookStatus.READING) {
                userBookStatus.setCurrentPage(0);
            }
            if (userBookStatus.getStatus() == BookStatus.READ) {
                userBookStatus.setCurrentPage(userBookStatus.getBook().getPageCount());
            }
            return userBookStatusRepository.save(userBookStatus);
        }
    }

    public void deleteUserBookStatus(Long id) {
        if (!userBookStatusRepository.existsById(id)) {
            throw new NoSuchElementException("User book status with id " + id + " does not exist.");
        }
        userBookStatusRepository.deleteById(id);
    }

    public UserBookStatus updateCurrentPage(Long id, Integer newPage) {
        UserBookStatus userBookStatus = userBookStatusRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("UserBookStatus with id " + id + " not found"));
        if (userBookStatus.getStatus() != BookStatus.READING) {
            throw new IllegalArgumentException("Cannot update page number for book that is not in READING status");
        }

        userBookStatus.setCurrentPage(newPage);
        if (userBookStatus.getCurrentPage().equals(userBookStatus.getBook().getPageCount())) {
            userBookStatus.setStatus(BookStatus.READ);
        }
        return userBookStatusRepository.save(userBookStatus);
    }

    public UserBookStatus updateRating(Long id, Integer rating) {
        UserBookStatus userBookStatus = userBookStatusRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("UserBookStatus with id " + id + " not found"));

        userBookStatus.setRating(rating);
        return userBookStatusRepository.save(userBookStatus);
    }

    public UserBookStatus updateReview(Long id, String review) {
        UserBookStatus userBookStatus = userBookStatusRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("UserBookStatus with id " + id + " not found"));

        userBookStatus.setReview(review);
        return userBookStatusRepository.save(userBookStatus);
    }

    public UserBookStatus updateFavorite(Long id, boolean favorite) {
        UserBookStatus userBookStatus = userBookStatusRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("UserBookStatus with id " + id + " not found"));

        userBookStatus.setFavorite(favorite);
        return userBookStatusRepository.save(userBookStatus);
    }

    public List<RatingReviewDTO> getAllReviewsForBook(String isbn) {
        List<UserBookStatus> statuses = userBookStatusRepository.findByBookIsbn(isbn);

        return statuses.stream()
                .filter(status -> status.getReview() != null || status.getRating() != null)
                .map(status -> new RatingReviewDTO(
                        status.getReview(),
                        status.getRating(),
                        status.getUser().getUsername()
                ))
                .collect(Collectors.toList());
    }

    public UserBookStatus markAsFinished(Long id, RatingReviewDTO ratingReviewDTO) {
        UserBookStatus userBookStatus = userBookStatusRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("UserBookStatus with id " + id + " not found"));

        userBookStatus.setStatus(BookStatus.READ);
        userBookStatus.setCurrentPage(userBookStatus.getBook().getPageCount());

        if (ratingReviewDTO != null) {
            if (ratingReviewDTO.getRating() != null) {
                userBookStatus.setRating(ratingReviewDTO.getRating());
            }
            if (ratingReviewDTO.getReview() != null) {
                userBookStatus.setReview(ratingReviewDTO.getReview());
            }
        }

        return userBookStatusRepository.save(userBookStatus);
    }

    public UserBookStatus getContinueReadingBook(Long userId) {
        List<UserBookStatus> readingBooks = userBookStatusRepository.findByUserIdAndStatus(userId, BookStatus.READING);

        if (readingBooks.isEmpty()) {
            throw new NoSuchElementException("No books currently being read by user with ID: " + userId);
        }

        Random random = new Random();
        UserBookStatus randomBookStatus = readingBooks.get(random.nextInt(readingBooks.size()));

        return randomBookStatus;
    }

    public Double getAverageRatingForBook(String isbn) {
        return userBookStatusRepository.findAverageRatingByBookIsbn(isbn);
    }

}
