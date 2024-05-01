package com.saccess.feedBack.services;

import com.saccess.feedBack.dto.Userdto;
import com.saccess.feedBack.entities.FeedbackNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

public class NotificationService {

  /*  private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public FeedbackNotif(ApplicationEventPublisher eventPublisher) {

        this.eventPublisher = eventPublisher;
    }

    public void sendNotification(String newsTitle, List<Userdto> userList) {
        for (Userdto user : userList) {
            sendNotificationToUser(newsTitle, user);
        }
    }
    private void sendNotificationToUser(String newsTitle, Userdto user) {
        String notificationMessage = "Un nouveau FeedBack a été ajoutée : " + newsTitle;
        FeedbackNotification notificationEvent = new FeedbackNotification(user, notificationMessage);
        eventPublisher.publishEvent(notificationEvent);
        System.out.println("Notification envoyée à l'utilisateur : " + user.firstName());
    }

   */
}
