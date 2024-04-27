package com.saccess.restaurant.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_order;
    private String location;
    private int quantity;
    private Float totalPrice;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime orderTime;
    private LocalDateTime deliveryTime;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToMany
    @JoinTable(
            name = "order_dish",
            joinColumns = @JoinColumn(name = "id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_dish")
    )
    private List<Dish> dishes;
}