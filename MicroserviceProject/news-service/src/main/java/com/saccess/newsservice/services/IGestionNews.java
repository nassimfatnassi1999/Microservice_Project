package com.saccess.newsservice.services;

import java.util.List;


import com.saccess.newsservice.entities.News;

public interface IGestionNews {

	public News getNews(Long id);
	public List<News> getAllNews();
	public News addNews(News n);
	public News updateNews(News n,Long id);
	public void deleteNews(Long id);
}
