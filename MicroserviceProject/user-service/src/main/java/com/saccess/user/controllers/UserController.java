package com.saccess.user.controllers;

import com.saccess.user.auth.JwtUtil;
import com.saccess.user.dto.DeleteAccountRequest;
import com.saccess.user.dto.LoginRequest;
import com.saccess.user.entities.User;
import com.saccess.user.services.GestionUserImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private GestionUserImpl userService;
    @Autowired
    private JwtUtil jwt;

    @PostMapping("/authenticate_user")
    public String authenticate(@RequestBody LoginRequest loginRequest){
        //Check if credentials are correct
        if(userService.login(loginRequest.getUsername(),loginRequest.getPassword())){
            // Get user
            User user = userService.getUserByEmail(loginRequest.getUsername());
            // return token
            return jwt.createToken(user);
        }
        return "incorrect";
    }

    @PostMapping("/register_user")
    public String register(@RequestBody User user) {
        // Check if the user with the same email already exists
        if (userService.getUserByEmail(user.getEmail()) != null) {
            // User with the same email already exists
            return "incorrect";
        }

        // add user to database
        userService.createUser(user);

        // Registration successful
        // gotta return token
        return jwt.createToken(user);
    }

    @PostMapping("/getbytoken")
    public User getUserByToken(@RequestBody String token) {
        String email = jwt.getEmailFromToken(token);
        return userService.getUserByEmail(email);
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestBody DeleteAccountRequest req){
        String email;
        try{
            email = jwt.getEmailFromToken(req.getToken());
        } catch (ExpiredJwtException | SignatureException | UnsupportedJwtException | MalformedJwtException exception){
            return "Your token is invalid/expired";
        }

        if(userService.getUserByEmail(email) == userService.getUserById(req.getUserId())){
            userService.deleteUser(req.getUserId());
            return "User deleted successfully";
        }
        return "You are not allowed";
    }
}
