package com.saccess.newsservice.services;

import java.util.List;


import com.saccess.newsservice.entities.News;
import org.springframework.web.multipart.MultipartFile;

public interface IGestionNews {

	public News getNews(Long id);
	public List<News> getAllNews();
	public News addNews(News n);
	public News updateNews(News n,Long id);
	public void deleteNews(Long id);
	public void addNewsWithImage(News news, MultipartFile imageFile);
}
