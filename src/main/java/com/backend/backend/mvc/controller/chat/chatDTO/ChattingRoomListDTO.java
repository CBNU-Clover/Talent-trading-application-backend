package com.backend.backend.mvc.controller.chat.chatDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@AllArgsConstructor
@Getter
public class ChattingRoomListDTO {
    private String seller;
    private Long roomId;
    ChattingRoomListDTO()
    {

    }
}
