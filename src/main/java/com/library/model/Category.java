package com.library.model;
//Represents different genres of books (e.g., Fiction, Science, History).

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Getters and Setters
}
