package com.cts.bibliotekar.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cts.bibliotekar.dto.BookRequestDTO;
import com.cts.bibliotekar.entity.Book;
import com.cts.bibliotekar.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping
	public List<Book> findAll() {
		return bookService.findAll();
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/{id}")
	public Optional<Book> findById(@PathVariable Long id) {
		return bookService.findById(id);
	}
	
//	Creating a book
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Book create(@RequestBody @Valid BookRequestDTO bookRequestDTO) {
		return bookService.save(bookRequestDTO);
	}
	
//	Update a book
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/{id}")
	public Book update(@PathVariable Long id, @RequestBody @Valid BookRequestDTO bookRequestDTO) {
		return bookService.update(id, bookRequestDTO);
	}
	
//	Delete a book
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		bookService.deleteById(id);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/find/title/{title}")
	public List<Book> findByTitle(@PathVariable String title) {
		return bookService.findByTitle(title);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/find/date-after/{date}")
	public List<Book> findByPublishedDateAfter(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date) {
		return bookService.findByPublishedDateAfter(date);
	}
	
}
