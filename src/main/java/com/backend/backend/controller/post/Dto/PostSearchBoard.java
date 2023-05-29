package com.backend.backend.controller.post.Dto;

import com.backend.backend.repository.postRepository.PostSearch;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
