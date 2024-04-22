package com.saccess.forumservice.services;

import com.saccess.forumservice.Entities.CommentairePost;
import com.saccess.forumservice.Repository.ICommentairePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionCommentairePostImpl implements IGestionCommentairePost{
    @Autowired
    ICommentairePostRepository commentaireRep;

    @Override
    public CommentairePost retreiveCommentaire(Long idComm) {
        return commentaireRep.findById(idComm).get();
    }

    @Override
    public List<CommentairePost> retrieveAllCommentaires() {
        return commentaireRep.findAll();
    }

    @Override
    public CommentairePost addCommentaire(CommentairePost commentairePost) {
        return commentaireRep.save(commentairePost);
    }

    @Override
    public CommentairePost updateCommentaire(CommentairePost commentairePost) {
        return commentaireRep.save(commentairePost);
    }

    @Override
    public void removeCommentaire(Long idComm) {
        commentaireRep.deleteById(idComm);
    }
}

