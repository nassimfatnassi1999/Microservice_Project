package com.saccess.restaurant.controllers;

import com.saccess.restaurant.entities.Dish;
import com.saccess.restaurant.entities.Restaurant;
import com.saccess.restaurant.services.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/restaurants")
@CrossOrigin(origins = "http://localhost:4200")

public class RestaurantController {

    @Autowired
    private IRestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<Page<Restaurant>> getAllRestaurants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Restaurant> restaurants = restaurantService.retrieveAllRestaurants(pageable);
        return ResponseEntity.ok(restaurants);
    }

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant createdRestaurant = restaurantService.addRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRestaurant);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.retrieveRestaurant(id);
        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        restaurant.setId_restaurant(id);
        Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurant);
        if (updatedRestaurant != null) {
            return ResponseEntity.ok(updatedRestaurant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.removeRestaurant(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/dishes")
    public ResponseEntity<List<Dish>> getDishesByRestaurantId(@PathVariable Long id) {
        List<Dish> dishes = restaurantService.getDishesByRestaurantId(id);
        if (dishes != null) {
            return ResponseEntity.ok(dishes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
