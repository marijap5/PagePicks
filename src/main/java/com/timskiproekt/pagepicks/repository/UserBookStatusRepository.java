package com.timskiproekt.pagepicks.repository;

import com.timskiproekt.pagepicks.domain.model.UserBookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserBookStatusRepository extends JpaRepository<UserBookStatus, Long> {
    List<UserBookStatus> findByUserId(Long userId);
    Optional<UserBookStatus> findByUserIdAndBookIsbn(Long userId, String bookISBN);
}
