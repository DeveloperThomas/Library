package demo.dto;

import demo.model.Book;
import demo.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentingDataTransfer {
    private Long id;
    private User user;
    private Book book;

    public RentingDataTransfer(Long id, User user, Book book){
        this.id = id;
        this.user = user;
        this.book = book;
    }
}
