package com.saccess.eventAndDonation.service;

import com.saccess.eventAndDonation.entities.Event;
import com.saccess.eventAndDonation.repositories.IEventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GestionEventImpl implements IGestionEvent{

    IEventRepository eventRepository;
    @Override
    public List<Event> retrieveAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event retrieveEvent(Long id_event) {
        return eventRepository.findById(id_event).orElse(null);
    }

    @Override
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void removeEvent(Long id_event) {

        eventRepository.deleteById(id_event);

    }
}
