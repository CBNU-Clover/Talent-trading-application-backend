package com.backend.backend.mvc.controller.popularPost.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class PopularPostResponse {
    private Long id;
    private String writerNickname;
    private String postName;
    private String content;
    private Long price;
    private Long review_size;

    private String date;

    //private byte[] image;
    public PopularPostResponse(Long id,String writerNickname, String postName, String content,Long price,String date,Long review_size) {
        this.writerNickname=writerNickname;
        this.id=id;
        this.postName = postName;
        this.content = content;
        this.price=price;
        this.date=date;
        this.review_size=review_size;
        //this.image=image;
    }



}