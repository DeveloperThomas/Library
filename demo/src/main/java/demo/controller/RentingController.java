package demo.controller;

import demo.dto.RentingDataTransfer;
import demo.model.Book;
import demo.security.CurrentUser;
import demo.security.UserPrincipal;
import demo.service.RentingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentings")
public class RentingController {

    private RentingService rentingService;

    @Autowired
    public RentingController(RentingService rentingService) {
        this.rentingService = rentingService;
    }

    @GetMapping("/all")
    public List<RentingDataTransfer> getAllRentings() {
        return rentingService.findAllRentingDataTransfer();
    }

    @PostMapping("/{bookId}")
    public RentingDataTransfer addRenting(@CurrentUser UserPrincipal userPrincipal, @PathVariable Long bookId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return rentingService.createRentingDataTransfer(auth.getPrincipal().toString(), bookId); }

    @DeleteMapping("/{id}")
    public void deleteRenting(@PathVariable Long id) {
        rentingService.deleteRentingDataTransferById(id);
    }
}
