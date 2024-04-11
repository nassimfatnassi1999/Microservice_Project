package com.saccess.restaurant.services;
import com.saccess.restaurant.entities.User;
import com.saccess.restaurant.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class IUserServiceImp implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id_user) {
        userRepository.deleteById(id_user);
    }

    @Override
    public User retrieveUser(Long id_user) {
        Optional<User> optionalUser = userRepository.findById(id_user);
        return optionalUser.orElse(null);
    }
}
