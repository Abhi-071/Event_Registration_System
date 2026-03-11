package com.abhi.event.controller;

import com.abhi.event.model.Event;
import com.abhi.event.model.EventCatalog;
import com.abhi.event.service.EventService;
import com.abhi.event.service.EventCatalogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventCatalogService eventCatalogService;

    // Home page (Registration page)
    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("events", eventCatalogService.getAllEvents());

        return "index";
    }

    // Register event
    @PostMapping("/register")
public String register(@ModelAttribute Event event, Model model) {

    if(!eventService.canRegister(event.getEventName())) {

        model.addAttribute("message", "Event is full!");

        return "index";
    }

    eventService.saveEvent(event);

    return "success";
}

    // View all registrations
    @GetMapping("/events")
    public String viewEvents(Model model) {

        model.addAttribute("events", eventService.getAllEvents());

        return "events";
    }

    // Delete registration
    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {

        eventService.deleteEvent(id);

        return "redirect:/events";
    }
}