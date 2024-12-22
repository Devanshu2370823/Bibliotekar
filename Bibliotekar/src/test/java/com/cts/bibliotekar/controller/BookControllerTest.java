package com.cts.bibliotekar.controller;

import com.cts.bibliotekar.dto.BookRequestDTO;
import com.cts.bibliotekar.entity.Book;
import com.cts.bibliotekar.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BookControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    public BookControllerTest() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void testFindAll_Success() throws Exception {
        // Arrange
        Book book = new Book("Book A", BigDecimal.valueOf(9.99), LocalDate.now());
        when(bookService.findAll()).thenReturn(Collections.singletonList(book));

        // Act & Assert
        mockMvc.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book A"));
    }

    @Test
    void testCreate_Success() throws Exception {
        // Arrange
        BookRequestDTO bookRequestDTO = new BookRequestDTO();
        bookRequestDTO.setTitle("Book B");
        bookRequestDTO.setPrice(BigDecimal.valueOf(19.99));
        bookRequestDTO.setPublishDate(LocalDate.now());

        Book savedBook = new Book("Book B", BigDecimal.valueOf(19.99), LocalDate.now());
        when(bookService.save(any(BookRequestDTO.class))).thenReturn(savedBook);

        // Act & Assert
        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Book B\", \"price\":19.99, \"publishDate\":\"2023-08-31\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Book B"));
    }
}
