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
public class CommentairePost implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComm ;
    private String contentComm ;
    private Long auteurId;
    private LocalDate creationDateComm ;
    @ElementCollection
    private List<Long> commLikedBy = new ArrayList<>();
    @ElementCollection
    private List<Long> commDislikedBy = new ArrayList<>();
    @ManyToOne
    Post postC;
}

