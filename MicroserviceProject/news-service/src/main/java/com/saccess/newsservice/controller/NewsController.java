package com.saccess.newsservice.controller;

import com.saccess.newsservice.entities.News;
import com.saccess.newsservice.services.IGestionNews;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
