package com.timskiproekt.pagepicks.repository;

import com.timskiproekt.pagepicks.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {

}
