package com.backend.backend.controller.post.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PostGetAllBoard {
    private Long id;
    private String postName;
    private String content;

    private Long price;
    public PostGetAllBoard( Long id,String postName, String content,Long price) {
        this.id=id;
        this.postName = postName;
        this.content = content;
        this.price=price;
    }
}
