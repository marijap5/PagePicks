package com.timskiproekt.pagepicks.domain.specification;

import com.timskiproekt.pagepicks.domain.model.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {
    public static Specification<Book> titleContains(String title) {
        return (root, query, builder) ->
                builder.like(builder.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Book> genreEquals(String genre) {
        return (root, query, builder) ->
                builder.equal(builder.lower(root.get("genre")), genre.toLowerCase());
    }

    public static Specification<Book> authorEquals(String author) {
        return (root, query, builder) ->
                builder.equal(builder.lower(root.get("author")), author.toLowerCase());
    }
}
