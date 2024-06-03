package com.timskiproekt.pagepicks.repository;

import com.timskiproekt.pagepicks.model.BookStatus;
import com.timskiproekt.pagepicks.model.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {
    boolean existsByUserIdAndBookISBN(Long userID, String bookISBN);
    Optional<UserBook> findByUserIdAndBookISBN(Long userID, String bookISBN);
    List<UserBook> findAllByUserIdAndStatus(Long userId, BookStatus bookStatus);
}
