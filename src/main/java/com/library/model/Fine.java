package com.library.model;
//Represents overdue book fines.

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Fine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Loan loan;

    private double amount;

    // Getters and Setters
}
