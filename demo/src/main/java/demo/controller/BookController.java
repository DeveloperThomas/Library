package demo.controller;

import demo.model.Book;
import demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService bookList;

    @Autowired
    public BookController(BookService bookService) {
        this.bookList = bookService;
    }

    @GetMapping("/all")
    public Iterable<Book> getAllBooks() {
        return bookList.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) {
        return bookList.findAllById(id);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookList.save(book);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book) {
        return bookList.save(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookList.deleteById(id);
    }
}
