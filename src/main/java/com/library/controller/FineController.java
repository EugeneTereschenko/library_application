package com.library.controller;
// Manages fines.
import com.library.model.Fine;
import com.library.service.FineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fines")
public class FineController {
    private final FineService fineService;

    public FineController(FineService fineService) {
        this.fineService = fineService;
    }

    @PostMapping
    public Fine addFine(@RequestBody Fine fine) {
        return fineService.addFine(fine);
    }

    @GetMapping
    public List<Fine> getAllFines() {
        return fineService.getAllFines();
    }
}
