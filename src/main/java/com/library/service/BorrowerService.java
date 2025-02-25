package com.library.service;
// Manages borrower details and registrations.
import com.library.model.Borrower;
import com.library.model.User;
import com.library.repository.BorrowerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BorrowerService {
    private final BorrowerRepository borrowerRepository;
    private final UserService userService;



    public Borrower registerBorrower(Borrower borrower) {
        return borrowerRepository.save(borrower);
    }

    public List<Borrower> getAllBorrowers() {
        return borrowerRepository.findAll();
    }

    public Optional<Borrower> getBorrowerById(Long id) {
        return borrowerRepository.findById(id);
    }

    public Optional<Borrower> getBorrowerByUserName(String username) {
        return borrowerRepository.findBorrowerByName(username);
    }


    public Optional<Borrower> createNewBorrowerByUserName(String username) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Borrower borrower = new Borrower();
        borrower.setEmail(user.getEmail());
        borrower.setName(user.getUsername());
        return Optional.of(borrowerRepository.save(borrower));
    }
}
