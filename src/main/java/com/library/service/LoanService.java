package com.library.service;
// Handles book borrow and return logic.
import com.library.dto.CardDTO;
import com.library.model.Card;
import com.library.model.Loan;
import com.library.model.User;
import com.library.repository.CardRepository;
import com.library.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final CardRepository cardRepository;
    private final UserService userService;


    public Loan issueLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public void returnBook(Long loanId) {
        loanRepository.deleteById(loanId);
    }

    public Optional<Card> getCardById(Long cardId) {
        return cardRepository.findById(cardId);
    }

    public Optional<Card> addCard(CardDTO cardDTO) {

        Long userId = Long.valueOf(cardDTO.getUserId());
        User user = userService.getUserById(userId);
        if (user == null) {
            return Optional.empty();
        }
        user.setFirstName(Optional.ofNullable(cardDTO.getFirstName()).orElse("John"));
        user.setLastName(Optional.ofNullable(cardDTO.getLastName()).orElse("Doe"));
        user.setEmail(Optional.ofNullable(cardDTO.getEmail()).orElse("test@test.com"));

        Card card = new Card();
        card.setCardName(Optional.ofNullable(cardDTO.getCardName()).orElse("John Doe"));
        card.setCardNumber(Optional.ofNullable(cardDTO.getCardNumber()).orElse("0000"));
        card.setExpirationDate(Optional.ofNullable(cardDTO.getExpirationDate()).orElse("01/01/2021"));
        card.setCvv(Optional.ofNullable(cardDTO.getCvv()).orElse("000"));
        card.setType(Optional.ofNullable(cardDTO.getType()).orElse("VISA"));
        card.setAddress(Optional.ofNullable(cardDTO.getAddress()).orElse("123 Main St"));
        card.setPhone(Optional.ofNullable(cardDTO.getPhone()).orElse("123-456-7890"));
        card.setUserId(Long.valueOf(Optional.ofNullable(cardDTO.getUserId()).orElse("0L")));

        return Optional.of(cardRepository.save(card));
    }

    public List<Card> getCardByUserId(Long userId) {
        return cardRepository.findAllCardByUserId(userId);
    }

}
