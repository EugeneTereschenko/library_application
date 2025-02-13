package com.library.model;
//Represents staff managing the library.

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Librarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    // Getters and Setters
}
