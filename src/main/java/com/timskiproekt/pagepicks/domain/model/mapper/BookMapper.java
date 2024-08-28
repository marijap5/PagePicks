package com.timskiproekt.pagepicks.domain.model.mapper;

import com.timskiproekt.pagepicks.domain.model.Book;
import com.timskiproekt.pagepicks.domain.model.dto.BookDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO bookToBookDTO(Book book);

    Book bookDTOToBook(BookDTO bookDTO);
}
