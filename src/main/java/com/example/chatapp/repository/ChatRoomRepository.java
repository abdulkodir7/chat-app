package com.example.chatapp.repository;

import com.example.chatapp.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {

    boolean existsBySenderIdAndReceiverId(Long senderId, Long receiverId);
    ChatRoom getBySenderIdAndReceiverId(Long senderId, Long receiverId);

    boolean existsByReceiverIdAndSenderId(Long receiverId, Long senderId);
    ChatRoom getByReceiverIdAndSenderId(Long receiverId, Long senderId);
}
