package com.library.model;
//Supports multi-branch libraries.
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class LibraryBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;

    // Getters and Setters
}
