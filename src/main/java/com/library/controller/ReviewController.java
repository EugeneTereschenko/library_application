package com.library.controller;
// Allows book reviews.
import com.library.dto.ReviewDTO;
import com.library.model.Book;
import com.library.model.Review;
import com.library.service.BookService;
import com.library.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final BookService bookService;

    @PreAuthorize("userService.isAuthenticated()")
    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody ReviewDTO reviewDTO) {
        Review review = reviewService.addReview(reviewDTO);
        return ResponseEntity.ok(review);
    }

    @PreAuthorize("userService.isAuthenticated()")
    @PostMapping("add/{id}")
    public ResponseEntity<Book> addBookFromReview(@PathVariable Long id) {
        log.info("Add book from review: {}", id);
        Book book = bookService.addBookFromReview(id);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }


    @PreAuthorize("userService.isAuthenticated()")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Review deleted successfully");
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("userService.isAuthenticated()")
    @GetMapping("user/{id}")
    public ResponseEntity<List<Review>> getAllReviewsByUserId(@PathVariable Long id) {
        log.info("Get all reviews by user id: {}", id);
        List<Review> reviews = reviewService.getAllReviewsByUserId(id);
        return ResponseEntity.ok(reviews);
    }

    @PreAuthorize("userService.isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }
}
