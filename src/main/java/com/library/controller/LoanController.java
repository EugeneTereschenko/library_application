package com.library.controller;
// Issues and returns books.
import com.library.model.Loan;
import com.library.service.LoanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public Loan issueLoan(@RequestBody Loan loan) {
        return loanService.issueLoan(loan);
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @DeleteMapping("/{id}")
    public void returnBook(@PathVariable Long id) {
        loanService.returnBook(id);
    }
}
