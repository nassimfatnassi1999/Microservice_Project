package com.saccess.eventAndDonation.service;

import com.saccess.eventAndDonation.entities.Donation;
import com.saccess.eventAndDonation.entities.Event;

import java.util.List;

public interface IGestionEvent {

    List<Event> retrieveAllEvents();
    Event retrieveEvent (Long id_event);
    Event addEvent(Event event);
    Event updateEvent (Event event);
    void removeEvent (Long id_event);
}
