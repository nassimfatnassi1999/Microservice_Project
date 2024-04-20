package com.saccess.feedBack.controllers;

import com.saccess.feedBack.dto.Userdto;
import com.saccess.feedBack.entities.Feedback;
import com.saccess.feedBack.entities.Status;
import com.saccess.feedBack.services.IGestionFeedBack;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/feedback")
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

    @GetMapping("/getFeedbackByDate")
    public List<Feedback> findByDateCreation(@RequestBody Date date){
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
}
