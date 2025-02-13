package com.library.controller;
// Handles staff management.
import com.library.model.Librarian;
import com.library.service.LibrarianService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/librarians")
public class LibrarianController {
    private final LibrarianService librarianService;

    public LibrarianController(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    @PostMapping
    public Librarian addLibrarian(@RequestBody Librarian librarian) {
        return librarianService.addLibrarian(librarian);
    }

    @GetMapping
    public List<Librarian> getAllLibrarians() {
        return librarianService.getAllLibrarians();
    }
}
