package com.saccess.eventAndDonation.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_event;
    private String name;
    private String description;
    private String location;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date endDate;
    private String topic;



    // private List<Userdto> sponsorsList;
    private  Long user_id;


}
