package demo.service;


import demo.dao.BookRepository;
import demo.dto.BookDataTransfer;
import demo.map.BookMap;
import demo.dto.AuthorDataTransfer;
import demo.model.Author;
import demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookService {

    private BookRepository bookRepository;
    private BookMap bookMap;
    private AuthorService authorService;

    @Autowired
    public BookService(BookRepository bookRepository, BookMap bookMap, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.bookMap = bookMap;
        this.authorService = authorService;
    }

    public BookDataTransfer findBookDataTransferById(Long id) {
        return bookMap.map(findBookById(id));
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find your book"));
    }

    public List<BookDataTransfer> findAllBooksDataTransfer() {
        List<BookDataTransfer> bookDataTransferList = new ArrayList<>();
        bookRepository.findAll().forEach(e -> bookDataTransferList.add(bookMap.map(e)));
        return bookDataTransferList;
    }

    public BookDataTransfer addBookDataTransfer(BookDataTransfer bookDataTransfer) {
        Book book = new Book();
        book.setTitle(bookDataTransfer.getTitle());
        book.setPublicationDate(bookDataTransfer.getPublicationDate());
        book.setRented(bookDataTransfer.getRented());
        addAuthorsToCurrentBook(book, bookDataTransfer.getAuthors());
        return bookMap.map(bookRepository.save(book));
    }

    private void addAuthorsToCurrentBook(Book book, List<AuthorDataTransfer> authors) {
        Set<Author> authorSet = new HashSet<>();
        List<Long> authorsIdList = new ArrayList<>();
        for(AuthorDataTransfer a: authors){
            authorsIdList.add(a.getId());
        }
        authorsIdList.forEach(e -> {
            authorSet.add(authorService.findAuthorById(e));
        });
        book.setAuthors(authorSet);

    }

    public BookDataTransfer updateBookDataTransfer(Long id, BookDataTransfer bookDataTransfer) {
        Book book = findBookById(id);

        if(bookDataTransfer.getTitle() != null) {
            book.setTitle(bookDataTransfer.getTitle());
        }
        if(bookDataTransfer.getPublicationDate() != null) {
            book.setPublicationDate(bookDataTransfer.getPublicationDate());
        }
        if(bookDataTransfer.getAuthors().size()>0) {
            addAuthorsToCurrentBook(book, bookDataTransfer.getAuthors());
        }
        return bookMap.map(bookRepository.save(book));
    }



    public void deleteBookDataTransferById(Long id) {
        bookRepository.deleteById(id);
    }
}
