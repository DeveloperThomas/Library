package demo.map;

import demo.dto.AuthorDataTransfer;
import demo.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMap implements Mapper<Author, AuthorDataTransfer> {

    @Override
    public AuthorDataTransfer map(Author author) {
        return new demo.dto.AuthorDataTransfer(author.getId(), author.getFirstName(), author.getLastName());
    }

}
