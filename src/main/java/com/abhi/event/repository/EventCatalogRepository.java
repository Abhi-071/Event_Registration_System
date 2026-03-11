package com.abhi.event.repository;

import com.abhi.event.model.EventCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventCatalogRepository extends JpaRepository<EventCatalog, Long> {

    @Query("SELECT e FROM EventCatalog e ORDER BY e.title")
    List<EventCatalog> getAllCatalogEvents();
}