package demo.controller;

import demo.dto.BookDataTransfer;
import demo.model.Book;
import demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public List<BookDataTransfer> getAllBooks() {
        return bookService.findAllBooksDataTransfer();
    }

    @GetMapping("/{id}")
    public BookDataTransfer getBookById(@PathVariable Long id) {
        return bookService.findBookDataTransferById(id);
    }

    @PostMapping
    public BookDataTransfer addBook(@RequestBody BookDataTransfer book) { return bookService.addBookDataTransfer(book); }

    @PutMapping("/{id}")
    public BookDataTransfer updateBook(@PathVariable Long id, @RequestBody BookDataTransfer bookDataTransfer) {
        return bookService.updateBookDataTransfer(id, bookDataTransfer);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBookDataTransferById(id);
    }
}
