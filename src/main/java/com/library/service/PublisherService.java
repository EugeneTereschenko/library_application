package com.library.service;
// Stores and retrieves publisher data.
import com.library.model.Publisher;
import com.library.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Publisher addPublisher(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }
}
