package com.library.model;
// Represents a person who borrows books.

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class Borrower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

}