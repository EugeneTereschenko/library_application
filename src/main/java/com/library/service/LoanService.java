package com.library.service;
// Handles book borrow and return logic.
import com.library.model.Loan;
import com.library.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan issueLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public void returnBook(Long loanId) {
        loanRepository.deleteById(loanId);
    }
}
