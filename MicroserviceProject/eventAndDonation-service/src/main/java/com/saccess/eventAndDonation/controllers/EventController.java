package com.saccess.eventAndDonation.controllers;

import com.saccess.eventAndDonation.entities.Donation;
import com.saccess.eventAndDonation.entities.Event;
import com.saccess.eventAndDonation.service.IGestionEvent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/event")

public class EventController {

    @Autowired
    IGestionEvent gestionEvent;


    @GetMapping("/getall")
    public List<Event> getall() {

        return gestionEvent.retrieveAllEvents();
    }

    @PostMapping("/addEvent")
    public Event addevent(@RequestBody Event event) {

        return gestionEvent.addEvent(event);
    }

    @GetMapping("/getevent/{id}")
    public Event getevent(@PathVariable("id") long id) {

        return gestionEvent.retrieveEvent(id);
    }

    @PutMapping("/updateevent")
    public Event modifyEvent(@PathVariable("id") Long id, @RequestBody Event updatedevent) {
        return gestionEvent.updateEvent(id, updatedevent);
    }
    @DeleteMapping("/removeEvent/{id}")
    @ResponseBody
    public void removeEvent(@PathVariable("id") Long id) {
        gestionEvent.removeEvent(id);

    }
}