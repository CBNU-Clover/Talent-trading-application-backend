package com.backend.backend.mvc.controller.chat;

import com.backend.backend.mvc.controller.chat.chatDTO.ChatMessageDTO;
import com.backend.backend.mvc.domain.chat.message.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @MessageMapping("/chat")
    public void enter(ChatMessageDTO message) {

        // 같은 채팅방에 있는 사람들에게 메시지를 다 보낸다.
        simpMessageSendingOperations.convertAndSend("/sub/room/"+message.getRoomId(),message);
        //채팅방에 채팅 저장

    }

}

