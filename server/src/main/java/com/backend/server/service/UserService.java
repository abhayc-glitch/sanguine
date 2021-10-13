package com.backend.server.service;

import com.backend.server.models.User;
import com.backend.server.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User retUsername = userRepository.findByUsername(username);
        if (retUsername == null){
            return null;
        }
        String name = retUsername.getUsername();
        String pwd = retUsername.getPassword();

        return new org.springframework.security.core.userdetails.User(name, pwd, null);
    }
}
