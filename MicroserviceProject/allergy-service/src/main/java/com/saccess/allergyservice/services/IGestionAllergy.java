package com.saccess.allergyservice.services;

import com.saccess.allergyservice.entities.Allergy;

import java.util.List;

public interface IGestionAllergy {
    public List<Allergy> retrieveAllAllergy();
    public Allergy addAllergy(Allergy allergy);
    public Allergy updateAllergy (Allergy allergy);
    public Allergy retrieveAllergy (Long id_Allergy);
    void removeAllergy(Long id_Allergy);
}
