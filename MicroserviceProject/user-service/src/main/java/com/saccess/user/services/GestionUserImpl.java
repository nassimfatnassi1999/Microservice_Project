package com.saccess.user.services;

import com.saccess.user.repositories.UserRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestionUserImpl implements IGestionUser {
    @Autowired
    UserRepoInterface repo;

}
