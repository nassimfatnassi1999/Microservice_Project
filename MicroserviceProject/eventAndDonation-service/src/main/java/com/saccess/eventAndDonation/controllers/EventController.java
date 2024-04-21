package com.saccess.eventAndDonation.controllers;

import com.saccess.eventAndDonation.dto.Userdto;
import com.saccess.eventAndDonation.entities.Donation;
import com.saccess.eventAndDonation.entities.Event;
import com.saccess.eventAndDonation.service.IGestionEvent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/addEventaaaa")
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
    @DeleteMapping("/delete/{id}")
    public void deleteEvent(@PathVariable("id") Long id){
        gestionEvent.deleteEvent(id);
    }

    @GetMapping("/getuserbyid/{id}")
    public Userdto getUserByID(@PathVariable("id")Long iduser){
        return gestionEvent.findUserById(iduser);
    }


    @GetMapping("/events/{name}")

    public List<Event> getByName(@PathVariable("name")String name) {


        return gestionEvent.findByName(name);
    }

    @PostMapping("/addEvent")
    public ResponseEntity<String> addEventWithImage(@RequestParam("name") String name,
                                                   @RequestParam("topic") String topic,
                                                   @RequestParam("user_id") Long userId,
                                                   @RequestParam("image") MultipartFile imageFile) {
        try {
            Event event = new Event();
            event.setName(name);
            event.setTopic(topic);
            event.setUser_id(userId);

            gestionEvent.addEventWithImage(event, imageFile);

            return ResponseEntity.status(HttpStatus.CREATED).body("Event ajoutée avec succès !");
        } catch (Exception e) {
            e.printStackTrace(); // ou tout autre traitement d'erreur approprié
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'event.");
        }
    }
}