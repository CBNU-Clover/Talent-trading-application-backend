package com.backend.backend.controller.post.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PostGetAllBoard {
    private String writer_nickname;
    private Long id;
    private String postName;
    private String content;
    private Long price;

    private String date;
    public PostGetAllBoard( Long id,String postName, String content,Long price,String date,String writer_nickname) {
        this.id=id;
        this.postName = postName;
        this.content = content;
        this.price=price;
        this.date=date;
        this.writer_nickname=writer_nickname;
    }
}
