package demo.controller;

import demo.dto.AuthorDataTransfer;
import demo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public List<AuthorDataTransfer> getAllAuthors(){
        return authorService.findAllAuthorsDataTransfer();
    }

    @GetMapping("/{id}")
    public AuthorDataTransfer getAuthorById(@PathVariable Long id){
        return authorService.findAuthorDataTransferById(id);
    }

    @PostMapping()
    public AuthorDataTransfer createAuthor(@RequestBody AuthorDataTransfer authorDataTransfer){
        return authorService.addAuthorDataTransfer(authorDataTransfer);
    }

    @PutMapping("/{id}")
    public AuthorDataTransfer updateAuthor(@PathVariable Long id, @RequestBody AuthorDataTransfer authorDataTransfer){
        return authorService.updateAuthorDataTransfer(id, authorDataTransfer);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id){
        authorService.deleteAuthorDataTransferById(id);
    }

}
