package com.example.chatapp.service;


import com.example.chatapp.entity.ChatRoom;
import com.example.chatapp.entity.Message;
import com.example.chatapp.payload.MessageDto;
import com.example.chatapp.projection.MessageProjection;
import com.example.chatapp.repository.ChatRoomRepository;
import com.example.chatapp.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final SimpMessagingTemplate messagingTemplate;

    private final ChatRoomRepository chatRoomRepository;

    private final MessageRepository messageRepository;


    public void sendMessage(MessageDto messageDto) {
        Long chatId = getChatId(messageDto.getSenderId(), messageDto.getReceiverId());
        Message savedMessage = messageRepository.save(new Message(
                messageDto.getSubject(),
                messageDto.getBody(),
                Long.valueOf(messageDto.getSenderId()),
                Long.valueOf(messageDto.getReceiverId()),
                chatId
        ));
        System.out.println("Receiver id: " + messageDto.getReceiverId());
        System.out.println(savedMessage);
        messagingTemplate.convertAndSendToUser(
                messageDto.getReceiverId(),
                "/topic/messages",
                savedMessage
        );
    }

    private Long getChatId(String senderId, String receiverId) {
        if (chatRoomRepository.existsBySenderIdAndReceiverId(
                Long.valueOf(senderId),
                Long.valueOf(receiverId)
        )) {
            ChatRoom chatRoomBySenderIdAndReceiverId =
                    chatRoomRepository.getBySenderIdAndReceiverId(
                            Long.valueOf(senderId),
                            Long.valueOf(receiverId)
                    );
            return chatRoomBySenderIdAndReceiverId.getChatId();
        } else if (chatRoomRepository.existsByReceiverIdAndSenderId(
                Long.valueOf(senderId),
                Long.valueOf(receiverId)
        )) {
            ChatRoom chatRoomByReceiverIdAndSenderId =
                    chatRoomRepository.getByReceiverIdAndSenderId(
                            Long.valueOf(senderId),
                            Long.valueOf(receiverId)
                    );
            return chatRoomByReceiverIdAndSenderId.getChatId();
        } else {
            Long chatId = Long.valueOf(senderId + receiverId);
            chatRoomRepository.save(new ChatRoom(
                    chatId,
                    Long.valueOf(senderId),
                    Long.valueOf(receiverId)
            ));
            if (senderId.equals(receiverId)) return chatId;
            chatRoomRepository.save(new ChatRoom(
                    chatId,
                    Long.valueOf(receiverId),
                    Long.valueOf(senderId)
            ));
            return chatId;
        }
    }

    public List<MessageProjection> getPersonalChatMessages(String senderId, String receiverId) {
        Long chatId = getChatId(senderId, receiverId);
        return messageRepository.findMessagesByChatId(chatId);
    }
}
