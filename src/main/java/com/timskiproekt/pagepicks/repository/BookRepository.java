package com.timskiproekt.pagepicks.repository;

import com.timskiproekt.pagepicks.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
     List<Book> findByGenre(String genre);
     List<Book> findByAuthor(String author);
     List<Book> findTop10ByOrderByTitleAsc();
}
