package com.saccess.feedBack.services;

import com.saccess.feedBack.entities.Feedback;

import java.time.LocalDate;
import java.util.List;

public interface IGestionFeedBack {
    List<Feedback> retrieveAllFeedbacks();
    Feedback addFeedBack(Feedback feedback);
    Feedback updateFeedback(Feedback feedback);
    Feedback retrieveFeedback(Long FeedbackID);
    List<Feedback> findByDateCreation(LocalDate creatDate);
    void removeFeedback(Long FeedbackID);
}
