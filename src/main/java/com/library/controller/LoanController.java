package com.library.controller;
// Issues and returns books.
import com.library.dto.CardDTO;
import com.library.model.Card;
import com.library.model.Loan;
import com.library.service.LoanService;
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
@RequestMapping("/loans")
public class LoanController {
    private final LoanService loanService;


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

    @PreAuthorize("userService.isAuthenticated()")
    @PostMapping("/cards")
    public ResponseEntity<Card> addCard(@RequestBody CardDTO cardDTO) {
        log.info("CardDTO " + cardDTO.toString() + " added");
        Optional<Card> newCard = loanService.addCard(cardDTO);
        return ResponseEntity.ok(newCard.get());
    }


    @PreAuthorize("userService.isAuthenticated()")
    @GetMapping("/cards/{id}")
    public ResponseEntity<List<Card>> getAllCardsByUserId(@PathVariable Long id) {
        List<Card> cards = loanService.getCardByUserId(id);
/*        Map<String, List<Card>> response = new HashMap<>();
        response.put("cards", cards);*/
        return ResponseEntity.ok(cards);
    }
}
