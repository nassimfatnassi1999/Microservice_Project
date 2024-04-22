package com.saccess.forumservice.controller;
import com.saccess.forumservice.Entities.Topic;
import com.saccess.forumservice.services.IGestionTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Topic")
public class TopicController {
    @Autowired
    IGestionTopic gestionTopic;

    @GetMapping("/getall")
    public List<Topic> getall() {
        return gestionTopic.retreiveAllTopic();}

    @GetMapping("/getId/{id}")
    public Topic getId(@PathVariable("id") Long id) {
        return gestionTopic.retreiveTopic(id);}

    @PostMapping("/add")
    public Topic add(@RequestBody Topic topic){
        return gestionTopic.addTopic(topic);
    }

    @DeleteMapping("/deleteID/{id}")
    public void delete(@PathVariable("id") long id){
        gestionTopic.removeTopic(id);
    }

    @PutMapping ("/update")
    public Topic update(@RequestBody Topic topic){
        return gestionTopic.updateTopic(topic);
    }

   /* @PostMapping("/AddTopicAndAssignToUserAndSection/{numU}-{numS}")
    public Topic AddPostAndAssignToTopicAndUser(@RequestBody Topic topic,
                                                @PathVariable("numU") Long auteurId,
                                                @PathVariable("numS") Long idSection) {
        return gestionTopic.AddTopicAndAssignToUserAndSection(topic, auteurId, idSection);
    }

  -----------------  @GetMapping("/sections/{sectionId}")
    public List<Topic> getTopicsBySectionIdOrderedByCreationDate(@PathVariable Long sectionId) {
        return gestionTopic.getTopicsBySectionIdOrderedByCreationDate(sectionId);
    }*/

}

