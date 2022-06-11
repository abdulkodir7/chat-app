package com.example.chatapp.payload;


import lombok.Data;

@Data
public class MessageDto {

    private String subject;
    private String senderId;
    private String receiverId;
    private String body;
}
