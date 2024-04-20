package com.saccess.eventAndDonation.service;

import com.saccess.eventAndDonation.dto.Userdto;
import com.saccess.eventAndDonation.entities.Donation;
import com.saccess.eventAndDonation.entities.Event;

import java.util.List;
import java.util.Optional;

public interface IGestionEvent {

    List<Event> retrieveAllEvents();
    Event retrieveEvent (Long id_event);
    Event addEvent(Event event);
    void removeEvent (Long id_event);


    Event updateEvent(Long id, Event updatedvent);

    Userdto findUserById(Long userid);




}
