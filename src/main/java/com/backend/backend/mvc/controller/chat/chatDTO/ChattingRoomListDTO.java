package com.backend.backend.mvc.controller.chat.chatDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChattingRoomListDTO {
    private String seller;
    private Long roomId;
    private String postname;
    private String buyer;
    private String post_price;
    private Long postId;
    private String post_image_url;
    private String seller_image_url;

}
