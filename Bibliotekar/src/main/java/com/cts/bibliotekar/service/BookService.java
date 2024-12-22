package com.cts.bibliotekar.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bibliotekar.dto.BookRequestDTO;
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
	
	public Book save(BookRequestDTO bookRequestDTO) {
		Book book = new Book();
		book.setTitle(bookRequestDTO.getTitle());
		book.setPrice(bookRequestDTO.getPrice());
		book.setPublishDate(bookRequestDTO.getPublishDate());
		return bookRepository.save(book);
	}
	
	public Book update(Long id, BookRequestDTO bookRequestDTO) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if(optionalBook.isPresent()) {
			Book book = optionalBook.get();
			book.setTitle(bookRequestDTO.getTitle());
			book.setPrice(bookRequestDTO.getPrice());
			book.setPublishDate(bookRequestDTO.getPublishDate());
			return bookRepository.save(book);
		}else {
			throw new RuntimeException("Book with ID "+ id + " not found.");
		}
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
