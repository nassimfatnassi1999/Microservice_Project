package com.saccess.newsservice.services;

import java.io.IOException;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.saccess.newsservice.entities.Image;
import com.saccess.newsservice.entities.News;
import com.saccess.newsservice.repositories.INewsRepository;
import com.saccess.newsservice.repositories.ImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class GestionNewsImpl implements IGestionNews {

	@Autowired
	INewsRepository newRepo;
	@Autowired
	private CloudinaryService cloudinaryService;
	@Autowired
	private ImageRepository imgRepo;

	@Override
	public News getNews(Long id) {
		// TODO Auto-generated method stub
		return newRepo.findById(id).get();
	}

	@Override
	public List<News> getAllNews() {
		// TODO Auto-generated method stub
		return newRepo.findAll();
	}

	@Override
	public News addNews(News n) {
		// TODO Auto-generated method stub
		return newRepo.save(n);
	}

	@Override
	public News updateNews(News n, Long id) {
		News N = newRepo.findById(id).get();
		if(N!=null) {
		 return	newRepo.save(n);
		}
		else
		return null;
	}

	@Override
	public void deleteNews(Long id) {
		// TODO Auto-generated method stub
		newRepo.deleteById(id);
	}
	//************************************************************************
	@Transactional
	public void addNewsWithImage(News news, MultipartFile imageFile) {
		try {
			// Enregistrer l'image sur Cloudinary
			Map uploadResult = cloudinaryService.upload(imageFile);

			// Récupérer l'URL de l'image téléchargée depuis Cloudinary
			String imageUrl = (String) uploadResult.get("url");

			// Enregistrer le lien URL de l'image dans la nouvelle
			Image image = new Image();
			image.setName(imageFile.getOriginalFilename());
			image.setImageURL(imageUrl);
			//save the image
			imgRepo.save(image);
			// set image to news
			news.setImage(image);
			//configurer la date actuelle
			LocalDate currentDate = LocalDate.now();
			Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			news.setDate(date);
			//Enregistrer la nouvelle dans la base de données
			newRepo.save(news);
		} catch (IOException e) {
			// Gérer toute exception
			e.printStackTrace(); // ou tout autre traitement d'erreur approprié
		}
	}

}
