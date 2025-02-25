package com.library.dto;

import lombok.Data;

@Data
public class BookDTO {
    private String title;
    private Long authorId;
    private Long categoryId;
    private double price;
    private int numOfBooks;
    // Getters and Setters
}
