package com.backend.backend.mvc.controller.post.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PostSearchBoard {
    private Long id;
    private String writerNickname;
    private String postName;
    private String content;
    private Long price;

    private String date;
    public PostSearchBoard(Long id,String writerNickname, String postName, String content,Long price,String date) {
        this.writerNickname=writerNickname;
        this.id=id;
        this.postName = postName;
        this.content = content;
        this.price=price;
        this.date=date;
    }



}
