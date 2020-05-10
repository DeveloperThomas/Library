package demo.service;


import demo.dao.BookRepository;
import demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<Book> findAllById(Long id) {
        return bookRepository.findById(id);
    }

    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDatabase() {
        save(new Book(1L, "1984", LocalDate.of(1999, 1, 1)));
        save(new Book(2L, "New Brave World", LocalDate.of(1992, 2, 3)));
    }
}
