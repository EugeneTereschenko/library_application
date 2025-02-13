package com.library.model;
//Allows borrowers to review and rate books.

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Borrower borrower;

    private String reviewText;
    private int rating;

    // Getters and Setters
}