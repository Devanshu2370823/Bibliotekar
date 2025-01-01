package com.cts.bibliotekar.service;

import com.cts.bibliotekar.dto.BookRequestDTO;
import com.cts.bibliotekar.entity.Book;
import com.cts.bibliotekar.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    public BookServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById_Success() {
        // Arrange
        Book book = new Book(null, "Java", BigDecimal.valueOf(9.99), LocalDate.now());
        book.setId(1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        // Act
        Optional<Book> result = bookService.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Java", result.get().getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testSave_Success() {
        // Arrange
        BookRequestDTO bookRequestDTO = new BookRequestDTO();
        bookRequestDTO.setTitle("C++");
        bookRequestDTO.setPrice(BigDecimal.valueOf(19.99));
        bookRequestDTO.setPublishDate(LocalDate.now());

        Book savedBook = new Book(null, "C++", BigDecimal.valueOf(19.99), LocalDate.now());
        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

        // Act
        Book result = bookService.save(bookRequestDTO);

        // Assert
        assertNotNull(result);
        assertEquals("C++", result.getTitle());
        verify(bookRepository, times(1)).save(any(Book.class));
    }
}

