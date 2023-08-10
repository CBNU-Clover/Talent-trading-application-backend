package com.backend.backend.mvc.controller.chat.chatDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDTO {
    private String type;
    private String sender;
    private String roomId;
    private Object data;
    public void setSender(String sender)
    {
        this.sender=sender;
    }
    public void newConnect(){
        this.type="new";
    }
    public void closeConnect()
    {
        this.type="close";
    }

}
