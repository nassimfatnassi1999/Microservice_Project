package com.saccess.allergyservice.services;

import com.saccess.allergyservice.entities.Allergy;
import com.saccess.allergyservice.repositories.IAllergyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class GestionAllergyImp implements IGestionAllergy{
    IAllergyRepository allergyRepository;

    @Override
    public List<Allergy> retrieveAllAllergy() {
        return allergyRepository.findAll();
    }

    @Override
    public Allergy addAllergy(Allergy allergy) {
        return allergyRepository.save(allergy);
    }

    @Override
    public Allergy updateAllergy(Allergy allergy) {
        return allergyRepository.save(allergy);
    }

    @Override
    public Allergy retrieveAllergy(Long id_Allergy) {
        return allergyRepository.findById(id_Allergy).orElse(null);
    }

    @Override
    public void removeAllergy(Long id_Allergy) {
        allergyRepository.deleteById(id_Allergy);

    }
}
