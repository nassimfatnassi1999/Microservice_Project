package com.saccess.forumservice.services;

import com.saccess.forumservice.Entities.Topic;

import java.util.List;

public interface IGestionTopic {
    Topic retreiveTopic(Long idTopic);

    List<Topic> retreiveAllTopic();

    Topic addTopic(Topic topic);

    Topic updateTopic(Topic topic);

    void removeTopic(Long idTopic);

}
