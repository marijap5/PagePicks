package com.timskiproekt.pagepicks.service;

import com.timskiproekt.pagepicks.model.BookStatus;
import com.timskiproekt.pagepicks.model.UserBook;
import com.timskiproekt.pagepicks.repository.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBookService {

    private final UserBookRepository userBookRepository;

    @Autowired
    public UserBookService(UserBookRepository userBookRepository) {
        this.userBookRepository = userBookRepository;
    }

    public UserBook addToReadList(Long userId, String bookISBN) {
        if (userBookRepository.existsByUserIdAndBookISBN(userId, bookISBN)) {
            throw new IllegalArgumentException("This book is already in the reading list");
        }
        UserBook userBook = new UserBook();
        userBook.setStatus(BookStatus.TO_READ);

        return userBookRepository.save(userBook);
    }

    public UserBook addToAlreadyRead(Long userId, String bookISBN) {
        if (userBookRepository.existsByUserIdAndBookISBN(userId, bookISBN)) {
            throw new IllegalArgumentException("The book is already in the already Read.");
        }

        UserBook userBook = new UserBook();
        userBook.setStatus(BookStatus.READ);

        return userBookRepository.save(userBook);
    }

    public void updateStatus(Long userBookId, BookStatus status) {
        UserBook userBook = userBookRepository.findById(userBookId)
                .orElseThrow(() -> new IllegalArgumentException("Book relation not found."));

        userBook.setStatus(status);
        userBookRepository.save(userBook);
    }

}
