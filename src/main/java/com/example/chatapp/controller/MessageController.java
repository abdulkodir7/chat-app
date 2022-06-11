package com.example.chatapp.controller;


import com.example.chatapp.payload.MessageDto;
import com.example.chatapp.projection.MessageProjection;
import com.example.chatapp.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class MessageController {

    private final MessageService messageService;

    @ModelAttribute("messageDto")
    public MessageDto registerDto() {
        return new MessageDto();
    }

    @MessageMapping("/usermessage")
    public void sendMessage(MessageDto messageDto) throws InterruptedException {
        messageService.sendMessage(messageDto);
    }

    @GetMapping("/api/messages/{senderId}/{receiverId}")
    public List<MessageProjection> getPersonalChatMessages(@PathVariable String receiverId,
                                                           @PathVariable String senderId){
        return messageService.getPersonalChatMessages(senderId,receiverId);
    }
}
