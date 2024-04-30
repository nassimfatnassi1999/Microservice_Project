package com.saccess.user.controllers;

import com.saccess.user.auth.JwtUtil;
import com.saccess.user.dto.DeleteAccountRequest;
import com.saccess.user.dto.LoginRequest;
import com.saccess.user.dto.ResetPasswordRequest;
import com.saccess.user.entities.User;
import com.saccess.user.services.EmailService;
import com.saccess.user.services.GestionUserImpl;
import com.saccess.user.services.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.AllArgsConstructor;
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
import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    EmailService mailer;

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
                return new ResponseEntity<Object>(map, HttpStatus.OK);
                //return ResponseEntity.ok(token);
            }else {
                map.put("status", 400);
                map.put("message", "There was a problem");
                return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
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
            map.put("message", "You already have an account");
            map.put("status", 401);
            return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
        }

        // add user to database
        userService.createUser(user);

        // Registration successful
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        //Create token
        String token = jwt.createToken(userDetails);
        map.put("message", "Account created successfully");
        map.put("status", 201);
        map.put("token",token);
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
    

    @GetMapping("/getbyid/{id}")
    public User getUserById(@PathVariable("id")Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers(){return userService.getAllUsers();}

    @GetMapping("/sendmail")
    public void sendMail() {
        User user = new User();
        user.setFirstName("Youssef");
        user.setEmail("ayedy40@gmail.com");
        mailer.sendForgotPasswordEmail(user);
    }

    @PostMapping("/resetpassword")
    public ResponseEntity resetPassword(@RequestBody ResetPasswordRequest request){
        if(jwt.isPasswordToken(request.getToken())){
            String email;
            try{
                email = jwt.getEmailFromToken(request.getToken());
            } catch (ExpiredJwtException | SignatureException | UnsupportedJwtException | MalformedJwtException exception){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            User user = userService.getUserByEmail(email);
            userService.resetPassword(user.getId(),request.getPassword());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/resetpasswordrequest")
    public ResponseEntity resetPasswordRequest(@RequestBody String email){
        User user = userService.getUserByEmail(email);
        mailer.sendForgotPasswordEmail(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
