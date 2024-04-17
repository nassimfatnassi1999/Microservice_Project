package com.saccess.feedBack.repositories;

import com.saccess.feedBack.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Scanner;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.query.Param;
@Repository
public interface IFeedBackRepository extends JpaRepository<Feedback,Long> {
    @Query("SELECT f FROM Feedback f WHERE f.CreatedAt = :CreateDate")
    public List<Feedback> findDateCreation(@Param("CreatedAt") LocalDate CreatDate);

}
