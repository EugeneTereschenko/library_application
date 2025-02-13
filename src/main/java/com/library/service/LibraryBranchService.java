package com.library.service;
// Manages library branches.
import com.library.model.LibraryBranch;
import com.library.repository.LibraryBranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryBranchService {
    private final LibraryBranchRepository libraryBranchRepository;

    public LibraryBranchService(LibraryBranchRepository libraryBranchRepository) {
        this.libraryBranchRepository = libraryBranchRepository;
    }

    public LibraryBranch addLibraryBranch(LibraryBranch libraryBranch) {
        return libraryBranchRepository.save(libraryBranch);
    }

    public List<LibraryBranch> getAllLibraryBranches() {
        return libraryBranchRepository.findAll();
    }
}