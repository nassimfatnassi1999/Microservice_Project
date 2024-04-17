package com.saccess.eventAndDonation.repositories;

import com.saccess.eventAndDonation.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEventRepository extends JpaRepository<Event, Long> {
}
