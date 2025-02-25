package com.library.controller;
// Allows book reviews.
import com.library.dto.ReviewDTO;
import com.library.model.Review;
import com.library.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @PreAuthorize("userService.isAuthenticated()")
    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody ReviewDTO reviewDTO) {
        Review review = reviewService.addReview(reviewDTO);
        return ResponseEntity.ok(review);
    }

    @PreAuthorize("userService.isAuthenticated()")
    @GetMapping("user/{id}")
    public ResponseEntity<List<Review>> getAllReviewsByUserId(@PathVariable Long id) {
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
