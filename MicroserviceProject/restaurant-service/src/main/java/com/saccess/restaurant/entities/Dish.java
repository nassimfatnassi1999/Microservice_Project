package com.saccess.restaurant.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Dish {
    public enum DishCategory {
        Malfouf,
        Mlawi,
        Chapati,
        Fricasse,
        Crepe,
        Pizza,
        Makloub,
        sandwich
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_dish;
    private String name;
    private String description;
    private float price;
    private String photo;
    private DishCategory category;
    @ManyToOne
    DishOrder dishes;
    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    private Restaurant restaurant;
}
