package com.timskiproekt.pagepicks.repository;

import com.timskiproekt.pagepicks.model.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {
    boolean existsByUserIdAndBookISBN(Long userID, String bookISBN);
}
