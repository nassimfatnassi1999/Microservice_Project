package com.saccess.allergyservice.services;

import com.saccess.allergyservice.entities.Contact;
import com.saccess.allergyservice.repositories.IContactRespository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@Service
public class GestionContactImpl implements IGestionContact {
    @Autowired
    IContactRespository gestionContact;
    @Override
    public Contact addcontact(Contact contact) {
        contact.setDate(LocalDate.now());
        return gestionContact.save(contact);
    }

    @Override
    public void removeContact(Long id_contact) {
        gestionContact.deleteById(id_contact);

    }

    @Override
    public List<Contact> retrieveContact() {
        return gestionContact.findAll();
    }
}
