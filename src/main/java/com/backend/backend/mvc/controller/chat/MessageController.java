package com.backend.backend.mvc.controller.chat;

import com.backend.backend.mvc.controller.chat.chatDTO.ChatMessageDTO;
import com.backend.backend.mvc.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final ChatService chatService;

    @MessageMapping("/chatroom")
    public void sendMessage(ChatMessageDTO message) {

        // 같은 채팅방에 있는 사람들에게 메시지를 다 보낸다.
        simpMessageSendingOperations.convertAndSend("/sub/chatroom/"+message.getRoomId(),message);
        //채팅방에 채팅 저장
        chatService.save_chat(message);
    }

}

