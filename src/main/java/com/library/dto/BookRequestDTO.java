package com.library.dto;

import lombok.Data;

@Data
public class BookRequestDTO {
    private String title;
    private String authorName;
    private String categoryName;
    private double price;
    private int numOfBooks;
}
