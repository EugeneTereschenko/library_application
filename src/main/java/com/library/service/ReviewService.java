package com.library.service;
// Allows users to post reviews for books.
import com.library.dto.ReviewDTO;
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
            Optional<Book> book = bookService.getBookById(reviewDTO.getBookId());
            if (book.isEmpty()) {
                throw new RuntimeException("Book not found");
            }
            Optional<Borrower> borrower = borrowerService.getBorrowerById(reviewDTO.getBorrowerId());
            if (borrower.isEmpty()) {
                borrower = borrowerService.createNewBorrowerByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
            }

            Review review = new Review();
            review.setBook(book.get());
            review.setBorrower(borrower.get());
            review.setReviewText(reviewDTO.getReviewText());
            review.setRating(reviewDTO.getRating());
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
            throw new RuntimeException("Borrower not found");
        }
        return reviewRepository.findAllByBorrowerId(borrower.get().getId());
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
}