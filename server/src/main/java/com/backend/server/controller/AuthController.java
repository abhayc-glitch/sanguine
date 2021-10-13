package com.backend.server.controller;

import com.backend.server.dto.AuthRequest;
import com.backend.server.dto.AuthResponse;
import com.backend.server.models.User;
import com.backend.server.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// An annotation to help convert the response we get from request to JSON or XML
@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // Manages authentication requests and provides a clean way to implement the Auth Flow rather than doing raw
    @Autowired
    private AuthenticationManager authenticationManager;

    // Creates a API endpoint in the form of a post request.
    @PostMapping("/subs")
    // Method get our client to register to the application
    private ResponseEntity<?> subscribeClient(@RequestBody AuthRequest authenticationRequest) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        //Assigning the values we get to the 
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);

        try {
            userRepository.save(user);
        } catch(Exception e) {
            return ResponseEntity.ok(new AuthResponse("Error; Unsuccessful Subscription for " + username));
        }

        return ResponseEntity.ok(new AuthResponse("Subscription Successful for " + username));
    }

    @PostMapping("/auth")
    private ResponseEntity<?> authenticateClient(@RequestBody AuthRequest authenticationRequest) {
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();
        
        try {
            // Beginning to JWT
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch ( BadCredentialsException e) {
            return ResponseEntity.ok(new AuthResponse("Error during client authentication: "));
        }

        return ResponseEntity.ok(new AuthResponse("Subscription Authentication for " + username));
    }


}
