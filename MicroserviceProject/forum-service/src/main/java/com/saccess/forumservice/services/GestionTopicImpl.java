package com.saccess.forumservice.services;
import com.saccess.forumservice.Entities.Topic;
import com.saccess.forumservice.Repository.ITopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GestionTopicImpl implements IGestionTopic{
    @Autowired
    ITopicRepository topicRep;
    @Override
    public Topic retreiveTopic(Long idTopic) {
        return topicRep.findById(idTopic).get();
    }

    @Override
    public List<Topic> retreiveAllTopic() {
        return topicRep.findAll();
    }

    @Override
    public Topic addTopic(Topic topic) {
        return topicRep.save(topic);
    }

    @Override
    public Topic updateTopic(Topic topic) {
        return topicRep.save(topic);
    }

    @Override
    public void removeTopic(Long idTopic) {
        topicRep.deleteById(idTopic);
    }

  /*  @Override
    public Topic AddTopicAndAssignToUserAndSection(Topic topic, Long auteurId, Long idSection) {
        Section section = sectionRep.findById(idSection).orElseThrow(() -> new RuntimeException("Section not found."));
        String title = topic.getTitle();
        if (title == null || title.length() < 20 || title.length() > 100) {
            throw new IllegalArgumentException("Title length should be between 20 and 100 characters.");
        }
        topic.setCreationDateTopic(LocalDate.now());
        topic.setSection(section);
        return topicRep.save(topic);
    }



   -------------------- public List<Topic> getTopicsBySectionIdOrderedByCreationDate(Long sectionId) {
        return topicRep.findBySectionIdOrderByCreationDateTopicDesc(sectionId);
    }*/
}

