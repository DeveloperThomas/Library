package demo.service;

import demo.dao.RentingRepository;
import demo.dao.UserRepository;
import demo.dto.RentingDataTransfer;
import demo.map.RentingMap;
import demo.model.Book;
import demo.model.Renting;
import demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class RentingService {

    private RentingRepository rentingRepository;
    private RentingMap rentingMap;
    private BookService bookService;
    private UserRepository userRepository;

    @Autowired
    public RentingService(RentingRepository rentingRepository, RentingMap rentingMap, BookService bookService, UserRepository userRepository) {
        this.rentingRepository = rentingRepository;
        this.rentingMap = rentingMap;
        this.bookService = bookService;
        this.userRepository = userRepository;
    }

    public RentingDataTransfer createRentingDataTransfer(Object principal, Long bookId) {

        Collection<User> user = userRepository.findByUsername((String)principal);
        Book book = bookService.findBookById(bookId);
        if(!book.getRented()){
            Renting renting = new Renting();
            bookService.changeRented(book.getId());
            renting.setBook(book);
            renting.setUser(user.stream().findFirst().get());
            return rentingMap.map(rentingRepository.save(renting));
        }
        else throw new RuntimeException("Book was rented before!");
    }

    public void deleteRentingDataTransferById(Long id) {
        Renting renting = rentingRepository.findById(id).orElseThrow(() -> new RuntimeException("Renting not found"));
        bookService.changeRented(renting.getBook().getId());
        rentingRepository.deleteById(id);
    }

    public List<RentingDataTransfer> findAllRentingDataTransfer() {
        List<RentingDataTransfer> rentingDataTransferList = new ArrayList<>();
        rentingRepository.findAll().forEach(e -> rentingDataTransferList.add(rentingMap.map(e)));
        return rentingDataTransferList;
    }
}