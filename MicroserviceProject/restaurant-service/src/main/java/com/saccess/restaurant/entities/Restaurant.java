package com.saccess.restaurant.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Restaurant {
    public enum RestaurantCategory {
        Fast_Food,
        Cafe_Restaurant,
        Pizzeria,
        Cafe,
        OTHER
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_restaurant;
    private String name;
    private String logo;
    @Enumerated(EnumType.STRING)
    private RestaurantCategory category;
    private float averageRating;
    @OneToOne
    private Location location;
    private float waitTime;
    private boolean isEcoFriendly;
    private String contactInfo;
    private boolean delivery;

    @OneToMany(mappedBy = "restaurant")
    private List<Dish> menu;
}
