package com.saccess.restaurant.services;
import com.saccess.restaurant.entities.Restaurant;
import com.saccess.restaurant.repositories.IRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class IRestaurantServiceImp implements IRestaurantService {

    @Autowired
    private IRestaurantRepository iRestaurantRepository;

    @Override
    public List<Restaurant> retrieveAllRestaurants() {
        return (List<Restaurant>) iRestaurantRepository.findAll();
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return iRestaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) {
        return iRestaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant retrieveRestaurant(Long id) {
        return iRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public void removeRestaurant(Long id) {
        iRestaurantRepository.deleteById(id);
    }
}
