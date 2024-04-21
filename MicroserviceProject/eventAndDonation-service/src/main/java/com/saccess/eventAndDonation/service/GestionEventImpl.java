package com.saccess.eventAndDonation.service;

import com.cloudinary.utils.ObjectUtils;
import com.saccess.eventAndDonation.clients.UserClient;
import com.saccess.eventAndDonation.dto.Userdto;
import com.saccess.eventAndDonation.entities.Event;
import com.saccess.eventAndDonation.entities.Image_Event;
import com.saccess.eventAndDonation.repositories.IEventRepository;
import com.saccess.eventAndDonation.repositories.IImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
@AllArgsConstructor

public class GestionEventImpl implements IGestionEvent{

    private CloudinaryService_Event cloudinaryService;

    IEventRepository eventRepository;

    @Autowired
    private CloudinaryService_Event cloudinaryServiceEventervice;

    @Autowired
    private IImageRepository imgrepo;


    UserClient userClient;
    @Override
    public List<Event> retrieveAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event retrieveEvent(Long id_event) {
        return eventRepository.findById(id_event).orElse(null);
    }

    @Override
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }


    @Override
    public void deleteEvent(Long id_event) {
// Récupérer l'ID de l'image associée à la news
        Optional<Event> newsOptional = eventRepository.findById(id_event);
        if (newsOptional.isPresent()) {
            Event news = newsOptional.get();
            String imageURL = news.getImage().getImageURL();
            Long imageId = news.getImage().getId();
            // Supprimer image from Cloudinary
            deleteImageFromCloudinary(imageURL);
            // Supprimer la news de la base de données
            eventRepository.deleteById(id_event);
            // Supprimer l'entrée de l'image de la base de données
            imgrepo.deleteById(imageId);
        }

    }



    @Override
    public Event updateEvent(Long id, Event updatedvent) {
        Event E = eventRepository.findById(id).orElse(null);
        if (E != null) {
            E.setDate(updatedvent.getDate());
            E.setLocation(updatedvent.getLocation());
            E.setTopic(updatedvent.getTopic());


            return eventRepository.save(E);
        }

        return null;
    }
    @Override
    public Userdto findUserById(Long userid){
        var user = userClient.getUserById(userid);
        return user;
    }

    @Override
    public List<Event> findByName(String name) {
        return eventRepository.findByName(name);
    }

    @Override
    public void addEventWithImage(Event event, MultipartFile imageFile) {
        try {
            // Enregistrer l'image sur Cloudinary
            Map uploadResult = cloudinaryService.upload(imageFile);
            // 5oudh l'URL de l'image from  Cloudinary
            String imageUrl = (String) uploadResult.get("url");
            // Enregistrer le lien URL de l'image dans news
            Image_Event image = new Image_Event();
            image.setName(imageFile.getOriginalFilename());
            image.setImageURL(imageUrl);
            //save the image
            imgrepo.save(image);
            // set image to news
            event.setImage(image);
            //configurer la date actuelle
            LocalDate currentDate = LocalDate.now();
            Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            event.setDate(date);
            //Enregistrer la nouvelle dans la base de données
            eventRepository.save(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteImageFromCloudinary(String imageUrl) {
        try {
            // 5oudh imageID from URL
            String imageId = extractImageIdFromUrl(imageUrl);
            // Supprimer image from Cloudinary
            cloudinaryService.cloudinary.uploader().destroy(imageId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String extractImageIdFromUrl(String imageUrl) {
        // positionner id mel URl
        int lastSlashIndex = imageUrl.lastIndexOf("/");
        int lastDotIndex = imageUrl.lastIndexOf(".");
        // extract imageID from URL /blablabla
        return imageUrl.substring(lastSlashIndex + 1, lastDotIndex);
    }


}






