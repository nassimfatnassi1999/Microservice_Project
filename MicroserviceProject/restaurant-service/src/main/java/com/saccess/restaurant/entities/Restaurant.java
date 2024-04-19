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
    public enum Badge {
        Best_Seller,
        Best_Donnor,
        New
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
    @Enumerated(EnumType.STRING)
    private Badge badge;
    @OneToMany(mappedBy = "restaurant")
    private List<Dish> menu;
}
