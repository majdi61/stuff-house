package com.stuffhouse.myapp.service;


import com.stuffhouse.myapp.domain.Event;
import com.stuffhouse.myapp.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class EventService {

    private final EventRepository eventRepository;


    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;


    }

    //test
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public Page<Event> getEventsPage(Document document, Pageable pageable) {
        document = Optional.ofNullable(document).orElse(new Document());

        return eventRepository.filter(document, pageable);
    }

    public Optional<Event> getEventById(String id) {
        return eventRepository.findById(id);
    }


    public void deleteEvent(String id) {
        eventRepository.findById(id).ifPresent(eventRepository::delete);
    }


}
