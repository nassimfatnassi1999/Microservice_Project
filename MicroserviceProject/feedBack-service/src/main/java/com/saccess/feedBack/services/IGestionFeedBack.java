package com.saccess.feedBack.services;

import com.saccess.feedBack.dto.Userdto;
import com.saccess.feedBack.entities.Feedback;
import com.saccess.feedBack.entities.Status;
import com.saccess.feedBack.entities.Type;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IGestionFeedBack {
    List<Feedback> retrieveAllFeedbacks();
    Feedback addFeedBack(Feedback feedback);
    Feedback updateFeedback(Feedback feedback);
    Feedback retrieveFeedback(Long FeedbackID);
    List<Feedback> findByDateCreation(Date creatDate);
    List<Feedback> findByMounthCreation(Date creatDate);
    void removeFeedback(Long FeedbackID);
     Userdto findUserById(Long userid);
    List<Feedback> findByStatus(Status status);
    List<Feedback> findByType(Type type);
    void updateFeedbackDescription(Long feedbackId, String newDescription);
    List<Feedback> findRecentlyUpdatedFeedbacks(int nbr);

}
