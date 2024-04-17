package com.saccess.allergyservice.controllers;

import com.saccess.allergyservice.entities.Allergy;
import com.saccess.allergyservice.services.IGestionAllergy;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Allergy")
@AllArgsConstructor
public class AllergyControllerImpl {
IGestionAllergy gestionAllergy;
    @GetMapping("/getallAllergy")
    public List<Allergy> getAll(){
        return gestionAllergy.retrieveAllAllergy();
    }
    @GetMapping("/getAllergybyid/{id}")
    public Allergy getAllergyById(@PathVariable("id") Long id_Allergy){
        return gestionAllergy.retrieveAllergy(id_Allergy);
    }
    @PutMapping("/updateAllergy")
    public Allergy updateAllergy(@RequestBody Allergy allergy){
        return gestionAllergy.updateAllergy(allergy);
    }
    @DeleteMapping("/deleteallergy/{id}")
    public void deleteallergy(@PathVariable("id") Long id_allergy){
        gestionAllergy.removeAllergy(id_allergy);
    }


}
