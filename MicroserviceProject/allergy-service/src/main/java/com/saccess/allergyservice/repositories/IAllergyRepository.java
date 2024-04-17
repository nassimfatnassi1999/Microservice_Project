package com.saccess.allergyservice.repositories;

import com.saccess.allergyservice.entities.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAllergyRepository extends JpaRepository<Allergy,Long> {
}
