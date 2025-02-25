package com.library.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long bookId;
    private Long borrowerId;
    private String reviewText;
    private int rating;
}
