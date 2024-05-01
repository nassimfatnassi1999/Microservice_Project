package com.saccess.restaurant.services;
import com.saccess.restaurant.entities.Dish;
import com.saccess.restaurant.entities.Restaurant;
import com.saccess.restaurant.repositories.IRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class  IRestaurantServiceImp implements IRestaurantService {

    @Autowired
    private IRestaurantRepository iRestaurantRepository;

    @Override
    public Page<Restaurant> retrieveAllRestaurants(Pageable pageable) {
        return iRestaurantRepository.findAll(pageable);
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
    @Override
    public List<Dish> getDishesByRestaurantId(Long id_restaurant) {
        return iRestaurantRepository.findById(id_restaurant)
                .map(Restaurant::getMenu)
                .orElse(null);
    }
}
