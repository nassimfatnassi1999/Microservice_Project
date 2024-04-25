package com.saccess.feedBack.controllers;

import com.saccess.feedBack.dto.Userdto;
import com.saccess.feedBack.entities.Feedback;
import com.saccess.feedBack.entities.Status;
import com.saccess.feedBack.entities.Type;
import com.saccess.feedBack.repositories.IFeedBackRepository;
import com.saccess.feedBack.services.IGestionFeedBack;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@RequestMapping("/apiala/feedback")
@CrossOrigin("*")
public class FeedBackController {

    @Autowired
    IGestionFeedBack feedbackservice;

    @GetMapping("/getall")
    public List<Feedback> getall(){
        return feedbackservice.retrieveAllFeedbacks();
    }
    @GetMapping("/getbyid/{id}")
    public Feedback getById(@PathVariable("id") Long FeedbackID){
        return  feedbackservice.retrieveFeedback(FeedbackID);
    }
    @PostMapping("/add")
    public Feedback addFeedback(@RequestBody Feedback feedback){
        return  feedbackservice.addFeedBack(feedback);
    }
    @PutMapping("/update")
    public Feedback update(@RequestBody Feedback feedback){
    return  feedbackservice.updateFeedback(feedback);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long feedbackID){
        feedbackservice.removeFeedback(feedbackID);
    }

    @GetMapping("/getFeedbackByDate/{date}")
    public List<Feedback> findByDateCreation(@PathVariable("date") Date date){
        return feedbackservice.findByDateCreation(date);
    }
    @GetMapping("/getFeedbackByMonth")
    List<Feedback> findByMounthCreation(@RequestBody Date date){

        return feedbackservice.findByMounthCreation(date);
    }
    @GetMapping("/getUserByID/{id}")
    public Userdto getUserByID(@PathVariable("id")Long iduser){
        return feedbackservice.findUserById(iduser);
    }
    @GetMapping("/status/{status}")
    public List<Feedback> getFeedbacksByStatus(@PathVariable Status status) {
        return feedbackservice.findByStatus(status);
    }


    @PutMapping("/editDescription/{feedbackId}")
    public ResponseEntity<String> updateFeedbackDescription(@PathVariable Long feedbackId, @RequestBody String newDescription) {
        try {
            feedbackservice.updateFeedbackDescription(feedbackId, newDescription);
            return ResponseEntity.ok("Feedback description updated successfully.");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Feedback not found", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", e);
        }
    }
    @GetMapping("/getFeedBackByType/{type}")
    public List<Feedback> getFeedbacksByType(@PathVariable("type") Type type) {
        return feedbackservice.findByType(type);
    }
}
