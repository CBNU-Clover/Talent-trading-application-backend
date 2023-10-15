package com.backend.backend.mvc.controller.post.Dto;

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
    private Long my_review_size;
    private String image_url;
    public PostGetAllBoard( Long id,String postName, String content,Long price,String date,String writer_nickname,Long my_review_size,String image_url) {
        this.id=id;
        this.postName = postName;
        this.content = content;
        this.price=price;
        this.date=date;
        this.writer_nickname=writer_nickname;
        this.my_review_size=my_review_size;
        this.image_url=image_url;
    }
}
