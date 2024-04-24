package com.saccess.eventAndDonation.controllers;

import com.saccess.eventAndDonation.dto.Userdto;
import com.saccess.eventAndDonation.entities.Donation;
import com.saccess.eventAndDonation.entities.Event;
import com.saccess.eventAndDonation.entities.TypeEvent;
import com.saccess.eventAndDonation.service.IGestionEvent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apinoursine/event")
@CrossOrigin("*")
public class EventController {

    @Autowired
    IGestionEvent gestionEvent;


    @GetMapping("/getall")
    public List<Event> getall() {

        return gestionEvent.retrieveAllEvents();
    }

    @PostMapping("/addEventt")
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
    public ResponseEntity<String> addEventWithImage(@RequestParam("title") String title,
                                                    @RequestParam("topic") String topic,
                                                    @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                                    //@RequestParam("duration") int duration,
                                                    @RequestParam("typeEvent") TypeEvent typeEvent,
                                                    //@RequestParam("location") String location,
                                                    @RequestParam("location") String location,
                                                    //@RequestParam("image") Image_Event image,
                                                    @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        try {
            Event event = new Event();
            event.setTitle(title);
            event.setTopic(topic);
            event.setDate(date);
            event.setLocation(location);


            if (!imageFile.isEmpty()) {
                // Image is present, process it
                gestionEvent.addEventWithImage(event, imageFile);
            } else {
                // No image uploaded, potentially allow saving without image
                // eventServices.addEvent(event); // Assuming a method without image param
            }

            return ResponseEntity.status(HttpStatus.CREATED).body("event ajoutée avec succès !");
        } catch (Exception e) {
            e.printStackTrace(); // Consider more specific exception handling
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'event.");
        }

    }
}