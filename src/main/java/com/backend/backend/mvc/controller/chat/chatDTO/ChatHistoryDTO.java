package com.backend.backend.mvc.controller.chat.chatDTO;

import lombok.*;


@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatHistoryDTO {
    private Long Id;
    private String sender;
    private String content;
    private String date;
}
