package demo.map;

import demo.dto.RentingDataTransfer;
import demo.model.Renting;
import org.springframework.stereotype.Component;

@Component
public class RentingMap implements Mapper<Renting, RentingDataTransfer> {

    @Override
    public RentingDataTransfer map(Renting renting) {
        return new RentingDataTransfer(renting.getId(), renting.getUser(), renting.getBook());
    }
}
