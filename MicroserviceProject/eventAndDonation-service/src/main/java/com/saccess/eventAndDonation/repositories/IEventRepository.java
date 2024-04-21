package com.saccess.eventAndDonation.repositories;

import com.saccess.eventAndDonation.entities.Event;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT u FROM Event u WHERE u.name LIKE %:name% ")
    List<Event> findByName(@Param("name") String name);
}
