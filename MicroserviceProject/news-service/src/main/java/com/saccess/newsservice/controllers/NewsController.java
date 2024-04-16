package com.saccess.newsservice.controllers;

import com.saccess.newsservice.entities.News;
import com.saccess.newsservice.services.IGestionNews;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
//***********************************************************************
@PostMapping("/addNews")
public ResponseEntity<String> addNewsWithImage(@RequestParam("title") String title,
                                               @RequestParam("comment") String comment,
                                               @RequestParam("user_id") Long userId,
                                               @RequestParam("image") MultipartFile imageFile) {
    try {
        News news = new News();
        news.setTitle(title);
        news.setComment(comment);
        news.setUser_id(userId);

        news_service.addNewsWithImage(news, imageFile);

        return ResponseEntity.status(HttpStatus.CREATED).body("News ajoutée avec succès !");
    } catch (Exception e) {
        e.printStackTrace(); // ou tout autre traitement d'erreur approprié
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de la news.");
    }
}

}
