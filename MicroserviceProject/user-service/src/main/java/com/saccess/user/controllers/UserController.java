package com.saccess.user.controllers;

import com.saccess.user.auth.JwtUtil;
import com.saccess.user.dto.DeleteAccountRequest;
import com.saccess.user.dto.LoginRequest;
import com.saccess.user.entities.User;
import com.saccess.user.services.GestionUserImpl;
import com.saccess.user.services.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private GestionUserImpl userService;
    @Autowired
    private JwtUtil jwt;

    @PostMapping("/authenticate_user")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
            if(authentication.isAuthenticated()){
                //Create UserDetails for token creation
                UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
                //Create token
                String token = jwt.createToken(userDetails);
                map.put("status", 200);
                map.put("message", "success");
                map.put("token",token);
                return new ResponseEntity<Object>(map, HttpStatus.FOUND);
                //return ResponseEntity.ok(token);
            }else {
                map.put("status", 401);
                map.put("message", "There was a problem");
                return new ResponseEntity<Object>(map, HttpStatus.FOUND);
            }
        }catch( BadCredentialsException ex){
            map.put("message", "Bad credentials");
            map.put("status", 401);
            return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
        }catch ( LockedException e){
            map.put("message", "Your account is locked");
            map.put("status", 401);
            return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
        }catch ( DisabledException e){
            map.put("message", "Your account is disabled");
            map.put("status", 401);
            return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register_user")
    public ResponseEntity<?> register(@RequestBody User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        // Check if the user with the same email already exists
        if (userService.getUserByEmail(user.getEmail()) != null) {
            // User with the same email already exists
            map.put("message", "Your already have an account");
            map.put("status", 401);
            return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
        }

        // add user to database
        userService.createUser(user);
        // Registration successful
        // gotta return token
        map.put("message", "Account created successfully");
        map.put("status", 201);
        return new ResponseEntity<Object>(map, HttpStatus.CREATED);
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
