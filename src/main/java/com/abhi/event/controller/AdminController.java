package com.abhi.event.controller;

import com.abhi.event.model.Event;
import com.abhi.event.model.EventCatalog;
import com.abhi.event.repository.EventRepository;
import com.abhi.event.repository.EventCatalogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class AdminController {

    @Autowired
    private EventCatalogRepository eventCatalogRepository;

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {

        List<EventCatalog> events = eventCatalogRepository.findAll();

        Map<String, Long> eventStats = new LinkedHashMap<>();

        for(EventCatalog ev : events) {

            long count = eventRepository.countByEventName(ev.getTitle());

            eventStats.put(ev.getTitle(), count);
        }

        model.addAttribute("events", events);

        model.addAttribute("eventStats", eventStats);

        model.addAttribute("totalEvents", eventCatalogRepository.count());

        model.addAttribute("totalRegistrations", eventRepository.count());

        return "admin-dashboard";
    }

    // participants page
    @GetMapping("/admin/participants/{eventName}")
    public String participants(@PathVariable String eventName, Model model) {

        List<Event> participants = eventRepository.findByEventName(eventName);

        model.addAttribute("participants", participants);

        model.addAttribute("eventName", eventName);

        return "participants";
    }

    @GetMapping("/admin/events")
public String events(Model model){

    model.addAttribute("events", eventCatalogRepository.findAll());

    return "admin-events";
}

@GetMapping("/admin/analytics")
public String analytics(Model model){

    List<EventCatalog> events = eventCatalogRepository.findAll();

    Map<String, Long> stats = new LinkedHashMap<>();

    for(EventCatalog ev : events){

        stats.put(ev.getTitle(),
        eventRepository.countByEventName(ev.getTitle()));

    }

    model.addAttribute("stats", stats);

    return "admin-analytics";
}
}