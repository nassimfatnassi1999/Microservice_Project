package com.saccess.restaurant.services;

import com.saccess.restaurant.entities.User;

import java.util.List;

public interface IUserService {
    List<User> retrieveAllUsers();
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long id_user);
    User retrieveUser(Long id_user);
}