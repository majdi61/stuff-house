package com.stuffhouse.myapp.web.rest;

import com.stuffhouse.myapp.domain.Event;
import com.stuffhouse.myapp.service.EventService;
import com.turkraft.springfilter.boot.Filter;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.ResponseUtil;

import java.util.Optional;

@RestController
@RequestMapping("/api/event")
public class EventResource {

    private final EventService eventService;


    public EventResource(EventService eventService) {
        this.eventService = eventService;
    }

    @CrossOrigin(origins = "https://coart-doura.web.app/home")
    @PostMapping(path = "")
    public ResponseEntity<Event> saveEvent(@RequestBody Event event) {
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(eventService.saveEvent(event)));
    }

    @CrossOrigin(origins = "https://coart-doura.web.app/home")
    @GetMapping(path = "")
    public Page<Event> getEventsPage(@Filter(entityClass = Event.class) Document document, Pageable pageable) {
        return eventService.getEventsPage(document, pageable);
    }

    @CrossOrigin(origins = "https://coart-doura.web.app/home")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        return ResponseUtil.wrapOrNotFound(eventService.getEventById(id));
    }

    @CrossOrigin(origins = "https://coart-doura.web.app/home")
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable String id) {
        eventService.deleteEvent(id);
    }


}
