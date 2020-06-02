package demo.dto;

import demo.model.Book;
import demo.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentingDataTransfer {
    private Long id;
    private UserDataTransfer user;
    private BookDataTransfer book;

    public RentingDataTransfer(Long id, UserDataTransfer user, BookDataTransfer book){
        this.id = id;
        this.user = user;
        this.book = book;
    }
}
