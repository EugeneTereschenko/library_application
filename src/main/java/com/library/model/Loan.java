package com.library.model;
//Tracks which borrower borrowed which book and for how long.

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Borrower borrower;

    @ManyToOne
    private Book book;

    private Date loanDate;
    private Date dueDate;

    // Getters and Setters
}