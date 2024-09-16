package com.timskiproekt.pagepicks.repository;

import com.timskiproekt.pagepicks.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String>, JpaSpecificationExecutor<Book> {
    List<Book> findByGenre(String genre);

    List<Book> findByAuthor(String author);

    List<Book> findTop10ByOrderByTitleAsc();

    @Query("SELECT DISTINCT b.genre FROM Book b")
    List<String> findDistinctCategories();

    @Query("SELECT DISTINCT b.author FROM Book b")
    List<String> findDistinctAuthors();
}
