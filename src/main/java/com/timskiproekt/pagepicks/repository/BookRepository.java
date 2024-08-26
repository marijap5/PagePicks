package com.timskiproekt.pagepicks.repository;

import com.timskiproekt.pagepicks.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
     List<Book> findByGenre(String genre);
     List<Book> findByAuthor(String author);
     List<Book> findTop10ByOrderByTitleAsc();
}
