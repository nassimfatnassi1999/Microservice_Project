package com.saccess.forumservice.controller;
import com.saccess.forumservice.Entities.CommentairePost;
import com.saccess.forumservice.services.IGestionCommentairePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Commentaire")
public class CommentairePostController {
    @Autowired
    IGestionCommentairePost gestionCommentaire;

    @GetMapping("/getall")
    public List<CommentairePost> getall() {
        return gestionCommentaire.retrieveAllCommentaires();}

    @GetMapping("/getId/{id}")
    public CommentairePost getId(@PathVariable("id") Long id) {
        return gestionCommentaire.retreiveCommentaire(id);}

    @PostMapping("/add")
    public CommentairePost add(@RequestBody CommentairePost commentairePost){
        return gestionCommentaire.addCommentaire(commentairePost);
    }

    @DeleteMapping("/deleteID/{id}")
    public void delete(@PathVariable("id") long id){

        gestionCommentaire.removeCommentaire(id);
    }

    @PutMapping ("/update")
    public CommentairePost update(@RequestBody CommentairePost commentairePost){
        return gestionCommentaire.updateCommentaire(commentairePost);
    }
}
