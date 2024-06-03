package com.timskiproekt.pagepicks.service;

import com.timskiproekt.pagepicks.model.BookStatus;
import com.timskiproekt.pagepicks.model.User;
import com.timskiproekt.pagepicks.model.UserBook;
import com.timskiproekt.pagepicks.repository.UserBookRepository;
import com.timskiproekt.pagepicks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBookService {

    private final UserBookRepository userBookRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserBookService(UserBookRepository userBookRepository, UserRepository userRepository) {
        this.userBookRepository = userBookRepository;
        this.userRepository = userRepository;
    }

    public UserBook addToReadList(Long userId, String bookISBN) {
        if (userBookRepository.existsByUserIdAndBookISBN(userId, bookISBN)) {
            throw new IllegalArgumentException("This book is already in the reading list");
        }
        UserBook userBook = new UserBook();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        userBook.setUser(user);
        userBook.setBookISBN(bookISBN);
        userBook.setStatus(BookStatus.TO_READ);

        return userBookRepository.save(userBook);
    }

    public UserBook removeFromReadList(Long userId, String bookISBN) {
        UserBook userBook = userBookRepository.findByUserIdAndBookISBN(userId, bookISBN)
                .orElseThrow(() -> new IllegalArgumentException("Book not found."));
        userBookRepository.delete(userBook);
        return userBook;
    }

    public UserBook addToAlreadyRead(Long userId, String bookISBN) {
        if (userBookRepository.existsByUserIdAndBookISBN(userId, bookISBN)) {
            throw new IllegalArgumentException("This book is already read.");
        }
        UserBook userBook = new UserBook();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        userBook.setUser(user);
        userBook.setBookISBN(bookISBN);
        userBook.setStatus(BookStatus.READ);

        return userBookRepository.save(userBook);
    }

    public UserBook removeFromAlreadyRead(Long userId, String bookISBN) {
        UserBook userBook = userBookRepository.findByUserIdAndBookISBN(userId, bookISBN)
                .orElseThrow(() -> new IllegalArgumentException("Book not found."));
        userBookRepository.delete(userBook);
        return userBook;
    }

    public UserBook addToInProgress(Long userId, String bookISBN) {
        if (userBookRepository.existsByUserIdAndBookISBN(userId, bookISBN)) {
            throw new IllegalArgumentException("This book is already in progress.");
        }
        UserBook userBook = new UserBook();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        userBook.setUser(user);
        userBook.setBookISBN(bookISBN);
        userBook.setStatus(BookStatus.READING);

        return userBookRepository.save(userBook);
    }

    public UserBook removeFromInProgress(Long userId, String bookISBN) {
        UserBook userBook = userBookRepository.findByUserIdAndBookISBN(userId, bookISBN)
                .orElseThrow(() -> new IllegalArgumentException("Book not found."));
        userBookRepository.delete(userBook);
        return userBook;
    }

    public void updateStatus(Long userBookId, BookStatus status) {
        UserBook userBook = userBookRepository.findById(userBookId)
                .orElseThrow(() -> new IllegalArgumentException("Book relation not found."));

        userBook.setStatus(status);
        userBookRepository.save(userBook);
    }

    public List<UserBook> getUserBooks(Long userId, BookStatus status) {
        return userBookRepository.findAllByUserIdAndStatus(userId, status);
    }

}
