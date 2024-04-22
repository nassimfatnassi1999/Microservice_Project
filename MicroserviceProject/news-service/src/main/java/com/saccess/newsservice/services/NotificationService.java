package com.saccess.newsservice.services;

import com.saccess.newsservice.dto.UserDto;
import com.saccess.newsservice.entities.NotificationEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotificationService {

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public NotificationService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void sendNotification(String newsTitle, List<UserDto> userList) {
        for (UserDto user : userList) {
            sendNotificationToUser(newsTitle, user);
        }
    }
    private void sendNotificationToUser(String newsTitle, UserDto user) {
        String notificationMessage = "Une nouvelle news a été ajoutée : " + newsTitle;
        NotificationEvent notificationEvent = new NotificationEvent(user, notificationMessage);
        eventPublisher.publishEvent(notificationEvent);
        System.out.println("Notification envoyée à l'utilisateur : " + user.firstName());
    }
}
