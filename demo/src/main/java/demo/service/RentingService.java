package demo.service;

import demo.dao.RentingRepository;
import demo.dto.RentingDataTransfer;
import demo.map.RentingMap;
import demo.model.Book;
import demo.model.Renting;

public class RentingService {

    private RentingRepository rentingRepository;
    private RentingMap rentingMap;
    private BookService bookService;

    public RentingDataTransfer createRentingDataTransfer(RentingDataTransfer rentingDataTransfer) {

        if(!rentingDataTransfer.getBook().getRented()){
            Renting renting = new Renting();
            bookService.changeRented(rentingDataTransfer.getBook().getId());
            renting.setBook(rentingDataTransfer.getBook());
            renting.setUser(rentingDataTransfer.getUser());
            return rentingMap.map(rentingRepository.save(renting));
        }
        else throw new RuntimeException("Book was rented before!");
    }

    public void deleteRentingDataTransferById(Long id) {
        Renting renting = rentingRepository.findById(id).orElseThrow(() -> new RuntimeException("Renting not found"));
        bookService.changeRented(renting.getBook().getId());
        rentingRepository.deleteById(id);
    }
}