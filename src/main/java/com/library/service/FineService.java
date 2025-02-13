package com.library.service;
// Calculates and processes late return fines.
import com.library.model.Fine;
import com.library.repository.FineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FineService {
    private final FineRepository fineRepository;

    public FineService(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }

    public Fine addFine(Fine fine) {
        return fineRepository.save(fine);
    }

    public List<Fine> getAllFines() {
        return fineRepository.findAll();
    }
}
