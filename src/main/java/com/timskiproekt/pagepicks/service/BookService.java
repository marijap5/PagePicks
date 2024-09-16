package com.timskiproekt.pagepicks.service;

import com.timskiproekt.pagepicks.domain.model.Book;
import com.timskiproekt.pagepicks.domain.model.dto.BookDTO;
import com.timskiproekt.pagepicks.domain.model.mapper.BookMapper;
import com.timskiproekt.pagepicks.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.timskiproekt.pagepicks.domain.specification.BookSpecification;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::bookToBookDTO)
                .collect(Collectors.toList());
    }

    public Optional<BookDTO> getBookByISBN(String ISBN) {
        return bookRepository.findById(ISBN)
                .map(bookMapper::bookToBookDTO);
    }

    public BookDTO saveBook(BookDTO bookDTO) {
        Book book = bookMapper.bookDTOToBook(bookDTO);
        book.setImageUrl("https://covers.openlibrary.org/b/isbn/" + book.getIsbn() + "-L.jpg");
        Book savedBook = bookRepository.save(book);
        return bookMapper.bookToBookDTO(savedBook);
    }

    public BookDTO editBook(String ISBN, BookDTO updatedBookDTO) {
        Optional<Book> existingBookOptional = bookRepository.findById(ISBN);

        if (existingBookOptional.isPresent()) {
            Book existingBook = existingBookOptional.get();

            existingBook.setTitle(updatedBookDTO.getTitle());
            existingBook.setAuthor(updatedBookDTO.getAuthor());
            existingBook.setGenre(updatedBookDTO.getGenre());
            existingBook.setDescription(updatedBookDTO.getDescription());
            existingBook.setPageCount(updatedBookDTO.getPageCount());

            Book savedBook = bookRepository.save(existingBook);
            return bookMapper.bookToBookDTO(savedBook);
        } else {
            throw new IllegalArgumentException("Book with ISBN " + ISBN + " not found");
        }
    }

    public void deleteBook(String ISBN) {
        bookRepository.deleteById(ISBN);
    }

    public List<BookDTO> searchBooks(String title, String genre, String author) {
        Specification<Book> spec = Specification.where(
                        title != null ? BookSpecification.titleContains(title) : null)
                .and(genre != null ? BookSpecification.genreEquals(genre) : null)
                .and(author != null ? BookSpecification.authorEquals(author) : null);

        return bookRepository.findAll(spec)
                .stream()
                .map(bookMapper::bookToBookDTO)
                .collect(Collectors.toList());
    }

    public List<String> getAllCategories() {
        return bookRepository.findDistinctCategories();
    }

    public List<String> getAllAuthors() {
        return bookRepository.findDistinctAuthors();
    }
}
