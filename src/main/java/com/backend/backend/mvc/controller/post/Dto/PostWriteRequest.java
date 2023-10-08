package com.backend.backend.mvc.controller.post.Dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class PostWriteRequest {

    @NotEmpty(message = "작성자 정보는 반드시 들어가야 합니다")
    private String  writerNickname;

    @NotEmpty(message = "게시글 제목은 반드시 들어가야 합니다")
    private String postName;

    private String content;

    private Long price;

    private byte[] image;

    public PostWriteRequest(String writerNickname, String postName, String content,Long price,byte[] image) {
        this.writerNickname = writerNickname;
        this.postName = postName;
        this.content = content;
        this.price=price;
        this.image=image;
    }

    public PostWriteRequest() {
    }
}
