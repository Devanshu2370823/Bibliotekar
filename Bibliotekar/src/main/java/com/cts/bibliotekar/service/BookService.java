package com.cts.bibliotekar.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bibliotekar.entity.Book;
import com.cts.bibliotekar.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> findAll() {
		return bookRepository.findAll();
	}
	
	public Optional<Book> findById(Long id) {
		return bookRepository.findById(id);
	}
	
	public Book save(Book book) {
		return bookRepository.save(book);
	}
	
	public void deleteById(Long id) {
		bookRepository.deleteById(id);
	}
	
	public List<Book> findByTitle(String title) {
		return bookRepository.findByTitle(title);
	}
	
	public List<Book> findByPublishedDateAfter(LocalDate date) {
		return bookRepository.findByPublishedDateAfter(date);
	}
}
