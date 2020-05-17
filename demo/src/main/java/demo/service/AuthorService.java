package demo.service;

import demo.dao.AuthorRepository;
import demo.dto.AuthorDataTransfer;
import demo.dto.BookDataTransfer;
import demo.map.AuthorMap;
import demo.map.BookMap;
import demo.model.Author;
import demo.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMap authorMap;
    private final BookMap bookMap;

    public AuthorService(AuthorRepository authorRepository, AuthorMap authorMapper, BookMap bookMap) {
        this.authorRepository = authorRepository;
        this.authorMap = authorMapper;
        this.bookMap = bookMap;
    }

    public List<AuthorDataTransfer> findAllAuthorsDataTransfer() {
        List<AuthorDataTransfer> authorDataTransferList = new ArrayList<>();
        authorRepository.findAll().forEach(e -> authorDataTransferList.add(authorMap.map(e)));
        return authorDataTransferList;
    }

    public AuthorDataTransfer findAuthorDataTransferById(Long id) {
        return authorMap.map(findAuthorById(id));
    }

    public AuthorDataTransfer addAuthorDataTransfer(AuthorDataTransfer authorDataTransfer) {
        Author author = new Author();
        author.setFirstName(authorDataTransfer.getFirstName());
        author.setLastName(authorDataTransfer.getLastName());
        return authorMap.map(authorRepository.save(author));
    }

    public AuthorDataTransfer updateAuthorDataTransfer(Long id, AuthorDataTransfer authorDataTransfer) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot found your author"));
        if(authorDataTransfer.getFirstName() != null) {
            author.setFirstName(authorDataTransfer.getFirstName());
        }
        if(authorDataTransfer.getLastName() != null) {
            author.setLastName(authorDataTransfer.getLastName());
        }
        return authorMap.map(authorRepository.save(author));
    }

    public void deleteAuthorDataTransferById(Long id) {
        authorRepository.deleteById(id);
    }

    public Author findAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find your author"));
    }
}
