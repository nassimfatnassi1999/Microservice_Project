package com.saccess.restaurant.services;

import com.saccess.restaurant.entities.Restaurant;

import java.util.List;

public interface IRestaurantService {
    public List<Restaurant> retrieveAllRestaurants() ;
    public Restaurant addRestaurant(Restaurant restaurant) ;
    public Restaurant updateRestaurant(Restaurant restaurant) ;
    public void removeRestaurant(Long id_restaurant) ;
    public Restaurant retrieveRestaurant(Long id_restaurant) ;

}

