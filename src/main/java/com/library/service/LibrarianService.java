package com.library.service;
// Manages library staff.
import com.library.model.Librarian;
import com.library.repository.LibrarianRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrarianService {
    private final LibrarianRepository librarianRepository;

    public LibrarianService(LibrarianRepository librarianRepository) {
        this.librarianRepository = librarianRepository;
    }

    public Librarian addLibrarian(Librarian librarian) {
        return librarianRepository.save(librarian);
    }

    public List<Librarian> getAllLibrarians() {
        return librarianRepository.findAll();
    }
}
