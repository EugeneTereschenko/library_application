package com.library.repository;

import com.library.model.LibraryBranch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryBranchRepository extends JpaRepository<LibraryBranch, Long> {
}
