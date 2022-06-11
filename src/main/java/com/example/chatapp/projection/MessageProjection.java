package com.example.chatapp.projection;


public interface MessageProjection {

    Long getId();
    String getSubject();
    String getBody();
    Long getSenderId();
    Long getReceiverId();
}
