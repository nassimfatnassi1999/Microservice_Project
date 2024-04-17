package com.saccess.user.services;

import com.saccess.user.entities.User;

public interface IGestionUser {
    public void createUser(User user);
    public boolean login(String username, String password);
    public User getUserById(long id);
    public boolean resetPassword(long userId,String newPassword);
}
