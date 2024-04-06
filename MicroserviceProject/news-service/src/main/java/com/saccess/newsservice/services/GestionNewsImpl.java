package com.saccess.newsservice.services;

import java.util.List;

import com.saccess.newsservice.entities.News;
import com.saccess.newsservice.repository.INewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class GestionNewsImpl implements IGestionNews {

	@Autowired
	INewsRepository newRepo;

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
}
