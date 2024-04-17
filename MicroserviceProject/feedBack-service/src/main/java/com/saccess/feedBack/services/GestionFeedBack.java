package com.saccess.feedBack.services;

import com.saccess.feedBack.entities.Feedback;
import com.saccess.feedBack.repositories.IFeedBackRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class GestionFeedBack implements IGestionFeedBack {
   @Autowired
    IFeedBackRepository feedBackRepository;

    @Override
    public List<Feedback> retrieveAllFeedbacks() {

        return feedBackRepository.findAll();
    }

    @Override
    public Feedback addFeedBack(Feedback feedback) {

        return feedBackRepository.save(feedback);
    }

    @Override
    public Feedback updateFeedback(Feedback feedback) {

        return feedBackRepository.save(feedback);
    }

    @Override
    public Feedback retrieveFeedback(Long idfb) {
        return feedBackRepository.findById(idfb).get();
    }

    @Override
    public List<Feedback> findByDateCreation(LocalDate creatDate) {
        return feedBackRepository.findDateCreation(creatDate) ;
    }

    @Override
    public void removeFeedback(Long fbid) {
        feedBackRepository.deleteById(fbid);
    }





}
