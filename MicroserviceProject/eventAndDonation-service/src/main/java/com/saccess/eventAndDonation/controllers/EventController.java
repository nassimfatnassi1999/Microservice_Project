package com.saccess.eventAndDonation.controllers;

import com.saccess.eventAndDonation.dto.FullEventUser;
import com.saccess.eventAndDonation.dto.FullResponse;
import com.saccess.eventAndDonation.dto.UEvent;
import com.saccess.eventAndDonation.dto.Userdto;
import com.saccess.eventAndDonation.entities.Event;
import com.saccess.eventAndDonation.entities.Type;
import com.saccess.eventAndDonation.service.IGestionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/apinoursine/event")
@CrossOrigin("*")
public class EventController {

    @Autowired
    IGestionEvent gestionEvent;

    @GetMapping("/getAllUsers")
    public List<Userdto> getAllUsers(){return gestionEvent.getAllUsers();}

    @DeleteMapping("/deletebyuserid/{id}")
    public void deleteEventsByUserId(@PathVariable("id") Long id_user){
        gestionEvent.deleteEventsByUserId(id_user);
    }
    @GetMapping("/getAllUserEvent")
    public FullEventUser getAllUserEvent(){return gestionEvent.getAllUserEvent();}
    @GetMapping("/getEventByType/{type}")
    public List<Event>  getEventByType(@PathVariable("type")Type type){
        return gestionEvent.getEventByType(type);
    }
    @GetMapping("/getEventByDate/{date}")
    public List<Event> getEventByDate(@PathVariable("date")LocalDate date){
        return  gestionEvent.getEventByDate(date);
    }
    @GetMapping("/getFullResponse/{id}")
    public FullResponse getUserAndEvent(@PathVariable("id") Long id){
        return gestionEvent.getUserAndEvent(id);
    }

    @PostMapping("/addEventt")
    public Event addevent(@RequestBody Event event) {

        return gestionEvent.addEvent(event);
    }

    @GetMapping("/getevent/{id}")
    public Event getevent(@PathVariable("id") long id) {

        return gestionEvent.retrieveEvent(id);
    }

    @PutMapping("/updateevent/{id}")
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


    @GetMapping("/findEventByTitle/{title}")

    public List<Event> findEventByTitle(@PathVariable("title")String title) {


        return gestionEvent.findEventByTitle(title);
    }

    @PostMapping("/addEvent")
    public ResponseEntity<String> addEventWithImage(@RequestParam("title") String title,
                                                    @RequestParam("id_user") Long id_user,
                                                    @RequestParam("topic") String topic,
                                                    @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                                    @RequestParam("type") Type type,
                                                    @RequestParam("location") String location,
                                                    @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        try {
            Event event = new Event();
            event.setTitle(title);
            event.setTopic(topic);
            event.setType(type);
            event.setDate(date);
            event.setLocation(location);
            event.setUser_id(id_user);


            if (!imageFile.isEmpty()) {
                // Image is present, process it
                gestionEvent.addEventWithImage(event, imageFile);
            } else {
                // No image uploaded, potentially allow saving without image
                // eventServices.addEvent(event); // Assuming a method without image param
            }

            return ResponseEntity.status(HttpStatus.CREATED).body("event ajoute avec succes !");
        } catch (Exception e) {
            e.printStackTrace(); // Consider more specific exception handling
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'event.");
        }

    }

    @GetMapping("/getall")
    public List<UEvent> getall() {
        List<Event> le = gestionEvent.retrieveAllEvents();

        return le.stream().map(event -> {
            System.out.println(event.getUser_id());
            return new UEvent(event.getId_event(),event.getTitle(),event.getType(),
                event.getTopic(),
                event.getImage(),
                event.getLocation(),
                event.getDate(),
                gestionEvent.findUserById(event.getUser_id()));

        }).toList();
    }
}