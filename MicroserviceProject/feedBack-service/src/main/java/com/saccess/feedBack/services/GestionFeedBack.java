package com.saccess.feedBack.services;

import com.saccess.feedBack.entities.Feedback;
import com.saccess.feedBack.repositories.IFeedBackRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class GestionFeedBack implements IGestionFeedBack {
    /*@Autowired
    private JavaMailSender javaMailSender;
    private void sendFeedBackNotification(User user) {
        String userEmail = user.getEmail();
        String subject = "Confirmation de réception de votre feedback";
        String message = "\"Cher utilisateur,\\n\\n\" +\n" +
                "                \"Nous avons bien reçu votre feedback et nous vous en remercions. Votre opinion est précieuse pour nous \" +\n" +
                "                \"et nous l'utiliserons pour améliorer nos services. Si vous avez d'autres questions ou commentaires, \" +\n" +
                "                \"n'hésitez pas à nous contacter.\\n\\n\" +\n" +
                "                \"Cordialement,\\n\" +\n" +
                "                \"L'équipe de notre entreprise\"";

        SimpleMailMessage emailMessage = new SimpleMailMessage();
        emailMessage.setFrom("alaa.hamdi01@gmail.com");
        emailMessage.setTo(userEmail);
        emailMessage.setSubject(subject);
        emailMessage.setText(message);

        javaMailSender.send(emailMessage);
       // System.out.println("E-mail de notification envoyé à : " + userEmail);
    }*/
   @Autowired
    IFeedBackRepository feedBackRepository;

    @Override
    public List<Feedback> retrieveAllFeedbacks() {

        return feedBackRepository.findAll();
    }

    @Override
    public Feedback addFeedBack(Feedback feedback) {

        return feedBackRepository.save(feedback);
    }

    @Override
    public Feedback updateFeedback(Feedback feedback) {

        return feedBackRepository.save(feedback);
    }

    @Override
    public Feedback retrieveFeedback(Long idfb) {
        return feedBackRepository.findById(idfb).get();
    }

    @Override
    public List<Feedback> findByDateCreation(LocalDate creatDate) {
        return feedBackRepository.findDateCreation(creatDate) ;
    }

    @Override
    public void removeFeedback(Long fbid) {
        feedBackRepository.deleteById(fbid);
    }

}
