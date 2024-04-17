package com.saccess.forumservice.Entities;


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
    private int likes;
    private int dislikes;
    @ElementCollection
    private List<Long> likedBy = new ArrayList<>();
    @ElementCollection
    private List<Long> dislikedBy = new ArrayList<>();
    private boolean isApproved;
    private int Report;
    @ElementCollection
    private List<Long> reportedBy = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Topic topic;

    @ManyToOne
    private Post parentPost;

    @OneToMany(mappedBy = "parentPost", cascade = CascadeType.ALL)
    private List<Post> comments = new ArrayList<>();
}

