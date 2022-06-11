package com.example.chatapp.service;


import com.example.chatapp.model.User;
import com.example.chatapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;



    public User login(User user) {
        User userByEmail = userRepository.findByEmail(user.getEmail());
        if(userByEmail==null){
            userByEmail = userRepository.save(new User(user.getEmail()));
        }
        return userByEmail;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
