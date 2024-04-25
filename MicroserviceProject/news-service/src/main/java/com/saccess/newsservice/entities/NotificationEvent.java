package com.saccess.newsservice.entities;

import com.saccess.newsservice.dto.UserDto;
import org.springframework.context.ApplicationEvent;

public class NotificationEvent extends ApplicationEvent {
    private final UserDto user;
    private final String notificationMessage;

    public NotificationEvent(UserDto user, String notificationMessage) {
        super(user);
        this.user = user;
        this.notificationMessage = notificationMessage;
    }
    public UserDto getUser() {
        return user;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }
}
