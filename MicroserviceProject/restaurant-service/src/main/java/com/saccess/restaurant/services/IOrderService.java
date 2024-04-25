package com.saccess.restaurant.services;

import com.saccess.restaurant.entities.Dish;
import com.saccess.restaurant.entities.Order;

import java.util.List;

public interface IOrderService {
    List<Order> retrieveAllOrders();
    Order createOrder(Order order);
    Order updateOrder(Order order);
    void deleteOrder(Long id);
    Order retrieveOrder(Long id);
}