package com.timskiproekt.pagepicks.repository;

import com.timskiproekt.pagepicks.domain.model.Book;
import com.timskiproekt.pagepicks.domain.model.BookStatus;
import com.timskiproekt.pagepicks.domain.model.UserBookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserBookStatusRepository extends JpaRepository<UserBookStatus, Long> {
    List<UserBookStatus> findByUserId(Long userId);

    Optional<UserBookStatus> findByUserIdAndBookIsbn(Long userId, String bookISBN);

    List<UserBookStatus> findByBookIsbn(String isbn);

    @Query("SELECT u.book FROM UserBookStatus u GROUP BY u.book ORDER BY COUNT(u) DESC")
    List<Book> findTop10MostPopularBooks();


    @Query("SELECT u.book FROM UserBookStatus u WHERE u.rating IS NOT NULL GROUP BY u.book ORDER BY AVG(u.rating) DESC")
    List<Book> findTop10BestRatedBooks();

    List<UserBookStatus> findByUserIdAndStatus(Long userId, BookStatus status);

    @Query("SELECT AVG(ubs.rating) FROM UserBookStatus ubs WHERE ubs.book.isbn = :isbn AND ubs.rating IS NOT NULL")
    Double findAverageRatingByBookIsbn(String isbn);
}
