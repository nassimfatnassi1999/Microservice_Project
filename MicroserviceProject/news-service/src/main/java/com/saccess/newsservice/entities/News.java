package com.saccess.newsservice.entities;

import java.io.Serializable;
import java.util.Date;


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
public class News implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String comment;
	@OneToOne(cascade = CascadeType.MERGE)
	private Image image;
	private Date date;
	private Long user_id;
}
