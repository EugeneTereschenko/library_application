package com.library.service;
// Manages borrower details and registrations.
import com.library.model.Borrower;
import com.library.repository.BorrowerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowerService {
    private final BorrowerRepository borrowerRepository;

    public BorrowerService(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    public Borrower registerBorrower(Borrower borrower) {
        return borrowerRepository.save(borrower);
    }

    public List<Borrower> getAllBorrowers() {
        return borrowerRepository.findAll();
    }
}
