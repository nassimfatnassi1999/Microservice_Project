package com.saccess.allergyservice.repositories;

import com.saccess.allergyservice.entities.Allergy;
import com.saccess.allergyservice.entities.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAllergyRepository extends JpaRepository<Allergy,Long> {
    public Allergy getAllergyByName(String name);
    @Query("select a from Allergy a where a.level=:level")
    public List<Allergy> getAllergyByLevel(@Param("level") Level level);
}
