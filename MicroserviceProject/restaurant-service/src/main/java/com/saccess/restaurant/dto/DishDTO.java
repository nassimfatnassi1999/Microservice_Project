package com.saccess.restaurant.dto;

public record DishDTO(
        Long id,
        String name,
        String description,
        double price,
        String photo,
        String category

) {}
