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
import java.util.stream.Collectors;

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

    public BookDataTransfer changeRented(Long id){
        Book book = findBookById(id);
        book.setRented(!book.getRented());
        return bookMap.map(bookRepository.save(book));
    }

    public void deleteBookDataTransferById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<BookDataTransfer> search(String bookTitle, Integer bookYear, String bookAuthor) {
        List<BookDataTransfer> bookDataTransfers = new ArrayList<>();
        List<Book> foundBooksByTitle = new ArrayList<>();
        if(bookTitle != null){
            foundBooksByTitle = bookRepository.findAll().stream().filter(b -> b.getTitle().toLowerCase().contains(bookTitle.toLowerCase())).collect(Collectors.toList());
        }
        else
            foundBooksByTitle = bookRepository.findAll();

        List<Book> foundBooksByYear = new ArrayList<>();
        if(bookYear != null){
            foundBooksByYear = bookRepository.findAll().stream().filter(b -> b.getPublicationDate().getYear() == bookYear).collect(Collectors.toList());
        }
        else
            foundBooksByYear = bookRepository.findAll();

        List<Book> foundBooksByAuthor = new ArrayList<>();
        if(bookAuthor != null){
            foundBooksByAuthor = bookRepository.findAll().stream().filter(b -> b.getAuthors().contains(bookAuthor)).collect(Collectors.toList());
        }
        else
            foundBooksByAuthor = bookRepository.findAll();

        List<Book> foundBooks = new ArrayList<>(foundBooksByTitle);
        foundBooks.retainAll(foundBooksByYear);
        foundBooks.retainAll(foundBooksByAuthor);
        foundBooks.forEach(b -> bookDataTransfers.add(bookMap.map(b)));

        return bookDataTransfers;
    }
}
