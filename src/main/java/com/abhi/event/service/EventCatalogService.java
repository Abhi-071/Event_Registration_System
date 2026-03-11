package com.abhi.event.service;

import com.abhi.event.model.EventCatalog;
import com.abhi.event.repository.EventCatalogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventCatalogService {

    private final EventCatalogRepository repository;

    public EventCatalogService(EventCatalogRepository repository) {
        this.repository = repository;
    }

    public List<EventCatalog> getAllEvents() {
        return repository.getAllCatalogEvents();
    }
}