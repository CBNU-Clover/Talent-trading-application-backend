package com.backend.backend.mvc.controller.chat.chatDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChattingRoomDTO {
    private Long postId;
    private String seller;

    public ChattingRoomDTO() {

    }
}
