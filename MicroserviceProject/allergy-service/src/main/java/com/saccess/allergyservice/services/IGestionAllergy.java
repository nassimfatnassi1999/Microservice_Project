package com.saccess.allergyservice.services;

import com.saccess.allergyservice.dto.Userdto;
import com.saccess.allergyservice.entities.Allergy;
import com.saccess.allergyservice.entities.Level;

import java.time.LocalDate;
import java.util.List;

public interface IGestionAllergy {
    public List<Allergy> retrieveAllAllergy();
    public Allergy addAllergy(Allergy allergy);
    public Allergy updateAllergy (Allergy allergy);
    public Allergy retrieveAllergy (Long id_Allergy);
    void removeAllergy(Long id_Allergy);
    Allergy getAllergyByname(String name);
    List<Allergy> getAllergyLevel(Level level);
    Userdto findUserById(Long userid);
    public int getTotalAllergiesByDateRange(LocalDate startDate, LocalDate endDate);
}