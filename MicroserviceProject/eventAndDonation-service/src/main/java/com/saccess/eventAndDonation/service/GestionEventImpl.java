package com.saccess.eventAndDonation.service;

import com.saccess.eventAndDonation.clients.UserClient;
import com.saccess.eventAndDonation.dto.Userdto;
import com.saccess.eventAndDonation.entities.Event;
import com.saccess.eventAndDonation.repositories.IEventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GestionEventImpl implements IGestionEvent{

    IEventRepository eventRepository;
    UserClient userClient;
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
    public void removeEvent(Long id_event) {

        eventRepository.deleteById(id_event);

    }



    @Override
    public Event updateEvent(Long id, Event updatedvent) {
        Event E = eventRepository.findById(id).orElse(null);
        if (E != null) {
            E.setStartDate(updatedvent.getStartDate());
            E.setEndDate(updatedvent.getEndDate());
            E.setLocation(updatedvent.getLocation());
            E.setTopic(updatedvent.getTopic());


            return eventRepository.save(E);
        }

        return null;
    }
    @Override
    public Userdto findUserById(Long userid){
        var user = userClient.getUserById(userid);
        return user;
    }

}
