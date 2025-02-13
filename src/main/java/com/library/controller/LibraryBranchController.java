package com.library.controller;
// Handles multiple library branches.
import com.library.model.LibraryBranch;
import com.library.service.LibraryBranchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branches")
public class LibraryBranchController {
    private final LibraryBranchService libraryBranchService;

    public LibraryBranchController(LibraryBranchService libraryBranchService) {
        this.libraryBranchService = libraryBranchService;
    }

    @PostMapping
    public LibraryBranch addLibraryBranch(@RequestBody LibraryBranch libraryBranch) {
        return libraryBranchService.addLibraryBranch(libraryBranch);
    }

    @GetMapping
    public List<LibraryBranch> getAllLibraryBranches() {
        return libraryBranchService.getAllLibraryBranches();
    }
}
