package com.saccess.feedBack.services;

import com.saccess.feedBack.entities.Feedback;

import java.util.List;

public interface IGestionFeedBack {
    List<Feedback> retrieveAllFeedbacks();
    Feedback addFeedBack(Feedback feedback);
    Feedback updateFeedback(Feedback feedback);
    Feedback retrieveFeedback(Long FeedbackID);
    void removeFeedback(Long FeedbackID);
}
