package com.saccess.eventAndDonation.service;


import com.saccess.eventAndDonation.entities.Event;
import org.springframework.stereotype.Service;
// Importation des classes iText pour la manipulation de PDF
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.ByteArrayOutputStream;



@Service
public class PdfGenerationService {

    // Méthode pour générer un fichier PDF à partir des détails d'un événement
    public ByteArrayOutputStream generatePdf(Event event) {
        //Création d'un flux de sortie de tableau d'octets
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(baos);// Création d'un écrivain PDF à partir du flux de sortie
             PdfDocument pdf = new PdfDocument(writer);// Création d'un document PDF à partir de l'écrivain PDF
             Document document = new Document(pdf)) { // Création d'un document iText à partir du document PDF

            // Ajoutez les détails de l'événement au PDF sous forme de paragraphes
            document.add(new Paragraph("Title: " + event.getTitle()));
            document.add(new Paragraph("Topic: " + event.getTopic()));
            document.add(new Paragraph("Type: " + event.getType()));
            document.add(new Paragraph("Date: " + event.getDate()));
            document.add(new Paragraph("Location: " + event.getLocation()));

        } catch (Exception e) {
            e.printStackTrace();; // Affichage de la trace de la pile en cas d'erreur
        }

        return baos;// Retourne le flux de sortie de tableau d'octets contenant le PDF généré
    }

}
