package com.intellibet.service;


import com.intellibet.dto.EventForm;
import com.intellibet.mapper.EventMapper;
import com.intellibet.model.Event;
import com.intellibet.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private EventRepository eventRepository;

    public void save(EventForm eventForm) {

        Event event = eventMapper.map(eventForm);
        eventRepository.save(event);
    }

    public List<EventForm> retrieveAllEvents() {

        List<Event> allEvents = eventRepository.findAll();
        List<EventForm> result = new ArrayList<>();
        for (Event event : allEvents) {
            result.add(eventMapper.map(event));
        }
        return result;
    }
}
