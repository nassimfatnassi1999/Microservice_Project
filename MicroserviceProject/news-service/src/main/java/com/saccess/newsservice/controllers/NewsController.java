package com.saccess.newsservice.controllers;

import com.saccess.newsservice.entities.News;
import com.saccess.newsservice.services.IGestionNews;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@AllArgsConstructor
public class NewsController {
    IGestionNews news_service;

    @GetMapping("/getAll")
    public List<News> getAllNews(){
        return news_service.getAllNews();
    }

    @GetMapping("/get/{id}")
    public News getNewsById(@PathVariable("id") Long id){
        return news_service.getNews(id);
    }
    @PostMapping("/add")
    public News addNews(@RequestBody News n){
       return news_service.addNews(n);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteNews(@PathVariable("id") Long id){
        news_service.deleteNews(id);
    }
    @PutMapping("/edit/{id}")
    public News updateNews(@RequestBody News n,@PathVariable("id") Long id){
        return news_service.updateNews(n,id);
    }
}
