package com.abhi.event.repository;

import com.abhi.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    long countByEventName(String eventName);

    List<Event> findByEventName(String eventName);

}