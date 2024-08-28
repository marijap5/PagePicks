package com.timskiproekt.pagepicks.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private String description;
    private Integer pageCount;
}
