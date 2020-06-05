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

    public RentingDataTransfer createRentingDataTransfer(String principal, Long bookId) {

        String[] tmp = principal.split(";");
        int first = tmp[0].indexOf(":"); int second = tmp[0].indexOf(":", first + 1);
        String username = tmp[0].substring(second+2);

        Collection<User> user = userRepository.findByUsername(username);
        if(user==null || user.size()==0) throw new RuntimeException("User doesn't exist!");
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

    public void deleteRentingDataTransferById(String principal, Long id) {
        String[] tmp = principal.split(";");
        int first = tmp[0].indexOf(":"); int second = tmp[0].indexOf(":", first + 1);
        String username = tmp[0].substring(second+2);

        Collection<User> user = userRepository.findByUsername(username);
        if(user==null || user.size()==0) throw new RuntimeException("User doesn't exist!");
        Book book = bookService.findBookById(id);
        User userTmp = user.stream().findFirst().get();
        if(book.getRented()){
            Renting renting = rentingRepository.findByUserAndBook(userTmp, book).orElseThrow(() -> new RuntimeException("Renting not found or you aren't it owner"));
            bookService.changeRented(book.getId());
            rentingRepository.deleteById(renting.getId());
        }
        else throw new RuntimeException("This rented doesn't exist!");
    }

    public List<RentingDataTransfer> findAllRentingDataTransfer() {
        List<RentingDataTransfer> rentingDataTransferList = new ArrayList<>();
        rentingRepository.findAll().forEach(e -> rentingDataTransferList.add(rentingMap.map(e)));
        return rentingDataTransferList;
    }
}