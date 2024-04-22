package com.saccess.forumservice.Repository;
import com.saccess.forumservice.Entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITopicRepository extends JpaRepository<Topic, Long> {

//    ----------------- List<Topic> findBySectionIdOrderByCreationDateTopicDesc(Long sectionId);


}
