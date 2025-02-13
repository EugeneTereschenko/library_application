package com.library.model;
//Stores information about book publishers

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}