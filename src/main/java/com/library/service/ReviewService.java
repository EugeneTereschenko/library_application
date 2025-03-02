package com.library.service;
// Allows users to post reviews for books.

import com.library.dto.ReviewDTO;
import com.library.exception.BorrowerNotFoundException;
import com.library.model.Book;
import com.library.model.Borrower;
import com.library.model.Review;
import com.library.model.User;
import com.library.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ReviewService {
    private final BookService bookService;
    private final ReviewRepository reviewRepository;
    private final BorrowerService borrowerService;
    private final UserService userService;


    public Review addReview(ReviewDTO reviewDTO) {
        try {
            Optional<Book> book = bookService.getBookById(Optional.ofNullable(reviewDTO.getBookId()).orElse(0L));
            if (book.isEmpty()) {
                throw new RuntimeException("Book not found");
            }

            Optional<Borrower> borrower;

            if (reviewDTO.getBorrowerId() != null) {
                borrower = borrowerService.getBorrowerById(Optional.ofNullable(reviewDTO.getBorrowerId()).orElse(0L));

            } else {
                borrower = borrowerService.getBorrowerByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
                if (borrower.isEmpty()) {
                    borrower = borrowerService.createNewBorrowerByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
                }
            }

            if (borrower.isEmpty()) {
                throw new BorrowerNotFoundException("Borrower not found");
            }

            Review review = new Review();
            review.setBook(book.get());
            review.setBorrower(borrower.get());
            review.setReviewText(Optional.ofNullable(reviewDTO.getReviewText()).orElse(""));
            review.setRating(Optional.ofNullable(reviewDTO.getRating()).orElse(0));
            return reviewRepository.save(review);
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Error processing review: " + e.getMessage(), e);
        }
    }

    public List<Review> getAllReviewsByUserId(Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        Optional<Borrower> borrower = borrowerService.getBorrowerByUserName(user.getUsername());
        if (borrower.isEmpty()) {
            throw new BorrowerNotFoundException("Borrower not found");
        }
        return reviewRepository.findAllByBorrowerId(borrower.get().getId());
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}