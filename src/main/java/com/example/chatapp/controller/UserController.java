package com.example.chatapp.controller;


import com.example.chatapp.model.User;
import com.example.chatapp.payload.MessageDto;
import com.example.chatapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @GetMapping("/")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/")
    public String login(User user, Model model) {
        User currentUser = userService.login(user);
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("allUsers", allUsers);
        //TODO clear heroku database null in currentUserId
        return "messages";
    }

    @ModelAttribute("subjectFormDto")
    public MessageDto registerDto() {
        return new MessageDto();
    }
}
