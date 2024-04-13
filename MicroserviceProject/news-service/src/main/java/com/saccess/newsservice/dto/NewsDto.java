package com.saccess.newsservice.dto;

import com.saccess.newsservice.entities.Image;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;

import java.util.Date;

public record NewsDto(
         String title,
         String comment,
         Image image,
         Date date,
         Long user_id
) {
}
