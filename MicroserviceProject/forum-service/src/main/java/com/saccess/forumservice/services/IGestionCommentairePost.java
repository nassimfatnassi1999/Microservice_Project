package com.saccess.forumservice.services;

import com.saccess.forumservice.Entities.CommentairePost;

import java.util.List;

public interface IGestionCommentairePost {
    CommentairePost retreiveCommentaire(Long idComm);
    List<CommentairePost> retrieveAllCommentaires();
    CommentairePost addCommentaire(CommentairePost commentairePost);
    CommentairePost updateCommentaire(CommentairePost commentairePost);
    void removeCommentaire(Long idComm);
}