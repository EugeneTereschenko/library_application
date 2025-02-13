package com.library.controller;
// Manages publishers.
import com.library.model.Publisher;
import com.library.service.PublisherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping
    public Publisher addPublisher(@RequestBody Publisher publisher) {
        return publisherService.addPublisher(publisher);
    }

    @GetMapping
    public List<Publisher> getAllPublishers() {
        return publisherService.getAllPublishers();
    }
}