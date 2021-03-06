package com.example.chatapp.utils;


import com.example.chatapp.entity.User;
import com.example.chatapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String initMode;

    private final UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {
            userRepository.save(new User("Abdulkodir"));
            userRepository.save(new User("Alex"));
            userRepository.save(new User("Sasha"));
        }
    }
}
