package demo.controller;

import demo.dto.RentingDataTransfer;
import demo.service.RentingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rentings")
public class RentingController {

    private RentingService rentingService;

    @Autowired
    public RentingController(RentingService rentingService) {
        this.rentingService = rentingService;
    }

    @PostMapping
    public RentingDataTransfer addRenting(RentingDataTransfer rentingDataTransfer) { return rentingService.createRentingDataTransfer(rentingDataTransfer); }

    @DeleteMapping("/{id}")
    public void deleteRenting(@PathVariable Long id) {
        rentingService.deleteRentingDataTransferById(id);
    }
}
