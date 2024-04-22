package com.saccess.forumservice.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Post implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost ;
    private String titlePost;
    private String contentPost ;
    private LocalDate creationDatePost ;
    private Long auteurId;
    /*likes : List<User>
    dislikes: List<User>*/
    private int likes;
    private int dislikes;
    private boolean isApproved;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Report> reports= new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "postC")
    private List<CommentairePost> commentairePosts = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Topic topic;
}

