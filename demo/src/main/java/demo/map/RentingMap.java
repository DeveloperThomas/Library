package demo.map;

import demo.dto.BookDataTransfer;
import demo.dto.RentingDataTransfer;
import demo.dto.UserDataTransfer;
import demo.model.Renting;
import org.springframework.stereotype.Component;

@Component
public class RentingMap implements Mapper<Renting, RentingDataTransfer> {
    private final BookMap bookMap;
    private final UserMap userMap;

    public RentingMap(BookMap bookMap, UserMap userMap) {
        this.bookMap = bookMap;
        this.userMap = userMap;
    }

    @Override
    public RentingDataTransfer map(Renting renting) {
        UserDataTransfer userDataTransfer = userMap.map(renting.getUser());
        BookDataTransfer bookDataTransfer = bookMap.map(renting.getBook());
        return new RentingDataTransfer(renting.getId(), userDataTransfer, bookDataTransfer);
    }
}
