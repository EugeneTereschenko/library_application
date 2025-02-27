package com.library.controller;
//  Registers and fetches borrowers.
import com.library.model.Borrower;
import com.library.service.BorrowerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/borrowers")
public class BorrowerController {
    private final BorrowerService borrowerService;

    @PreAuthorize("userService.isAuthenticated()")
    @GetMapping("/user/{id}")
    public ResponseEntity<Map<String, String>> getBorrowerIdByUsername(@PathVariable String id) {
        Optional.ofNullable(borrowerService.getBorrowerIdByUserId(id)).get();
        Map<String, String> response = new HashMap<>();
        response.put("borrowerID", Optional.ofNullable(borrowerService.getBorrowerIdByUserId(id)).get().toString());
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("userService.isAuthenticated()")
    @PostMapping
    public Borrower registerBorrower(@RequestBody Borrower borrower) {
        return borrowerService.registerBorrower(borrower);
    }

    @PreAuthorize("userService.isAuthenticated()")
    @GetMapping
    public List<Borrower> getAllBorrowers() {
        return borrowerService.getAllBorrowers();
    }
}
