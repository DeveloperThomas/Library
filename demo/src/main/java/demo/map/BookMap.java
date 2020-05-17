package demo.map;

import demo.dto.AuthorDataTransfer;
import demo.dto.BookDataTransfer;
import demo.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookMap implements Mapper<Book, BookDataTransfer> {

    private final AuthorMap authorMap;

    public BookMap(AuthorMap authorMap) {
        this.authorMap = authorMap;
    }

    @Override
    public BookDataTransfer map(Book book) {
        List<AuthorDataTransfer> authorDataTransferList = new ArrayList<>();
        book.getAuthors().forEach(e -> authorDataTransferList.add(authorMap.map(e)));

        return new BookDataTransfer(book.getId(), book.getTitle(), book.getPublicationDate(), book.getRented(), authorDataTransferList);
    }
}
