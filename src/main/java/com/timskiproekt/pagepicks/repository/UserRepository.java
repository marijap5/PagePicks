package com.timskiproekt.pagepicks.repository;
import com.timskiproekt.pagepicks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

<<<<<<< Updated upstream
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
=======
public interface UserRepository extends JpaRepository<User, Integer> {
>>>>>>> Stashed changes
    Optional<User> findByUsername(String username);
}
