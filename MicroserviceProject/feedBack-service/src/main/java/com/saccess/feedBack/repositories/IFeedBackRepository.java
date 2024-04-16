package com.saccess.feedBack.repositories;

import com.saccess.feedBack.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFeedBackRepository extends JpaRepository<Feedback,Long> {

}
