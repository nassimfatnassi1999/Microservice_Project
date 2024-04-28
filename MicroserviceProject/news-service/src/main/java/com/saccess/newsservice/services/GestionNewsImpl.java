package com.saccess.newsservice.services;

import java.io.IOException;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.cloudinary.utils.ObjectUtils;
import com.saccess.newsservice.client.UserClient;
import com.saccess.newsservice.dto.UserDto;
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
	private  INewsRepository newRepo;
	@Autowired
	private CloudinaryService cloudinaryService;
	@Autowired
	private ImageRepository imgRepo;
	@Autowired
	private  NotificationService notif;
	@Autowired
	private UserClient userClient;

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
	public News updateNews(Long id,String title,String desc) {
		News N = newRepo.findById(id).get();
		N.setTitle(title);
		N.setComment(desc);
		return newRepo.save(N);
	}

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
	public  String extractImageIdFromUrl(String imageUrl) {
        // positionner id mel URl
		int lastSlashIndex = imageUrl.lastIndexOf("/");
		int lastDotIndex = imageUrl.lastIndexOf(".");
		// extract imageID from URL /blablabla
		return imageUrl.substring(lastSlashIndex + 1, lastDotIndex);
	}

	@Override
	public void deleteNews(Long id) {
		// Récupérer l'ID de l'image associée à la news
		Optional<News> newsOptional = newRepo.findById(id);
		if (newsOptional.isPresent()) {
			News news = newsOptional.get();
			String imageURL = news.getImage().getImageURL();
			Long imageId = news.getImage().getId();
			// Supprimer image from Cloudinary
			deleteImageFromCloudinary(imageURL);
			// Supprimer la news de la base de données
			newRepo.deleteById(id);
			// Supprimer l'entrée de l'image de la base de données
			imgRepo.deleteById(imageId);
		}
	}
	//************************************************************************
	@Transactional
	public void addNewsWithImage(News news, MultipartFile imageFile) {
		try {
			// Enregistrer l'image sur Cloudinary
			Map uploadResult = cloudinaryService.upload(imageFile);
			// 5oudh l'URL de l'image from  Cloudinary
			String imageUrl = (String) uploadResult.get("url");
			Image image = new Image();
			image.setName(imageFile.getOriginalFilename());
			image.setImageURL(imageUrl);
			imgRepo.save(image);
			news.setImage(image);
			LocalDate currentDate = LocalDate.now();
			Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			news.setDate(date);
			newRepo.save(news);
			//envoie notif to user
			//List<UserDto> allUsers = userClient.getAllUsers();
			//notif.sendNotification(news.getTitle(),allUsers);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<UserDto> getallUsersFromYoussef(){
		return userClient.getAllUsers();
	}

	public  List<News> getAllNewsOrderByDate(){
		return  newRepo.findAllOrder();
	}

}
