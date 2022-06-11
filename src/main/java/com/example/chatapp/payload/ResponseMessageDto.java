package com.example.chatapp.payload;

import lombok.Data;

@Data
public class ResponseMessageDto {

    private String subject;
    private String senderId;
    private String receiverId;
    private String body;
}
