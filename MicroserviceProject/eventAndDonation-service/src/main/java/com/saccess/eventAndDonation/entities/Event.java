package com.saccess.eventAndDonation.entities;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_event;
    private String title;
    @Enumerated(EnumType.STRING)
    private TypeEvent typeEvent;
    @Column(nullable = false)
    private String topic;
    @OneToOne(cascade = CascadeType.MERGE)
    private Image_Event image;
    private String location;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date date;
    // private List<Userdto> sponsorsList;
    private  Long user_id;


}
