package com.cts.bibliotekar;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cts.bibliotekar.entity.Book;
import com.cts.bibliotekar.repository.BookRepository;

@SpringBootApplication
public class BibliotekarApplication {

    private static final Logger log = LoggerFactory.getLogger(BibliotekarApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BibliotekarApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(BookRepository bookRepository) {
        return (args) -> {

            Book b1 = new Book(null, "Java", BigDecimal.valueOf(9.99), LocalDate.of(2023, 8, 31));
            Book b2 = new Book(null, "C++", BigDecimal.valueOf(19.99), LocalDate.of(2023, 7, 31));
            Book b3 = new Book(null, "Python", BigDecimal.valueOf(29.99), LocalDate.of(2023, 6, 10));
            Book b4 = new Book(null, "JavaScript", BigDecimal.valueOf(39.99), LocalDate.of(2023, 5, 5));

            bookRepository.save(b1);
            bookRepository.save(b2);
            bookRepository.save(b3);
            bookRepository.save(b4);

            log.info("findAll(), expect 4 books");
            log.info("-------------------------------");
            for (Book book : bookRepository.findAll()) {
                log.info(book.toString());
            }
            log.info("\n");

            Optional<Book> optionalBook = bookRepository.findById(1L);
            optionalBook.ifPresent(obj -> {
                log.info("Book found with findById(1L):");
                log.info("--------------------------------");
                log.info(obj.toString());
                log.info("\n");
            });

            log.info("Book found with findByTitle('Python')");
            log.info("--------------------------------------------");
            bookRepository.findByTitle("Python").forEach(b -> {
                log.info(b.toString());
                log.info("\n");
            });

            log.info("Book found with findByPublishedDateAfter(), after 2023/7/1");
            log.info("--------------------------------------------");
            bookRepository.findByPublishedDateAfter(LocalDate.of(2023, 7, 1)).forEach(b -> {
                log.info(b.toString());
                log.info("\n");
            });

//            Below lines will delete C++ from database.
            
//            bookRepository.deleteById(2L);
//            log.info("Book delete where ID = 2L");
//            log.info("--------------------------------------------");

            log.info("findAll() again, expect 4 books");
            log.info("-------------------------------");
            for (Book book : bookRepository.findAll()) {
                log.info(book.toString());
            }
            log.info("\n");
        };
    }
}
