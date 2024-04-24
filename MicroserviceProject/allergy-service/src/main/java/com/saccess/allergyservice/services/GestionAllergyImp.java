package com.saccess.allergyservice.services;

import com.saccess.allergyservice.clients.UserClient;
import com.saccess.allergyservice.dto.FullResponse;
import com.saccess.allergyservice.dto.Userdto;
import com.saccess.allergyservice.entities.Allergy;
import com.saccess.allergyservice.entities.Level;
import com.saccess.allergyservice.repositories.IAllergyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@Service
public class GestionAllergyImp implements IGestionAllergy{
    @Autowired
    IAllergyRepository allergyRepository;
    @Autowired
    UserClient userClient;

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

    @Override
    public Allergy getAllergyByname(String name) {
        return allergyRepository.getAllergyByName(name);
    }

    @Override
    public List<Allergy> getAllergyLevel(Level level) {
        return allergyRepository.getAllergyByLevel(level);
    }

    @Override
    public Userdto findUserById(Long userid) {
        return userClient.getUserById(userid);
    }

    @Override
    public int getTotalAllergiesByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Allergy> allergies = allergyRepository.getTotalAllergiesByDateRange(startDate, endDate);
        return allergies.size()+1;
    }

    @Override
    public FullResponse getUserAndAllergy(Long id) {
        Userdto user = userClient.getUserById(id); //user jebneh
        List<Allergy> allergies = allergyRepository.getAllAleergybyUserId(id);

        return new FullResponse(user,allergies);
    }

}
