package com.timskiproekt.pagepicks.repository;

import com.timskiproekt.pagepicks.model.UserBookStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBookStatusRepository extends JpaRepository<UserBookStatus, Long> {
    List<UserBookStatus> findByUserId(Long userId);
}
