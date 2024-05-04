package com.saccess.restaurant.controllers;

import com.saccess.restaurant.entities.Dish;
import com.saccess.restaurant.entities.Restaurant;
import com.saccess.restaurant.services.IRestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/restaurants")
@CrossOrigin(origins = "http://localhost:4200")

public class RestaurantController {

    @Autowired
    private IRestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.retrieveAllRestaurants();
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
    @PostMapping("/{id}/dishes")
    public ResponseEntity<Dish> addDishToRestaurant(@PathVariable(value = "id") Long restaurantId,
                                                    @Valid @RequestBody Dish dish) {
        Dish createdDish = restaurantService.addDishToRestaurant(restaurantId, dish);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdDish.getId_dish())
                        .toUri())
                .body(createdDish);
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
