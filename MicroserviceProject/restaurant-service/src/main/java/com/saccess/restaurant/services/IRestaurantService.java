package com.saccess.restaurant.services;

import com.saccess.restaurant.entities.Dish;
import com.saccess.restaurant.entities.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IRestaurantService {
    public Page<Restaurant> retrieveAllRestaurants(Pageable pageable);

    public Restaurant addRestaurant(Restaurant restaurant) ;
    public Restaurant updateRestaurant(Restaurant restaurant) ;
    public void removeRestaurant(Long id_restaurant) ;
    public Restaurant retrieveRestaurant(Long id_restaurant) ;
    List<Dish> getDishesByRestaurantId(Long id_restaurant);

}

