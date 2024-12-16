package com.cts.bibliotekar.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cts.bibliotekar.entity.Book;
import com.cts.bibliotekar.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@GetMapping
	public List<Book> findAll() {
		return bookService.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Book> findById(@PathVariable Long id) {
		return bookService.findById(id);
	}
	
//	Creating a book
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Book create(@RequestBody Book book) {
		return bookService.save(book);
	}
	
//	Update a book
	@PutMapping
	public Book update(@RequestBody Book book) {
		return bookService.save(book);
	}
	
//	Delete a book
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		bookService.deleteById(id);
	}
	
	@GetMapping("/find/title/{title}")
	public List<Book> findByTitle(@PathVariable String title) {
		return bookService.findByTitle(title);
	}
	
	@GetMapping("/find/date-after/{date}")
	public List<Book> findByPublishedDateAfter(@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date) {
		return bookService.findByPublishedDateAfter(date);
	}
	
}
