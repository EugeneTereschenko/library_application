package com.library.controller;
//  Registers and fetches borrowers.
import com.library.model.Borrower;
import com.library.service.BorrowerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrowers")
public class BorrowerController {
    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @PostMapping
    public Borrower registerBorrower(@RequestBody Borrower borrower) {
        return borrowerService.registerBorrower(borrower);
    }

    @GetMapping
    public List<Borrower> getAllBorrowers() {
        return borrowerService.getAllBorrowers();
    }
}
