package com.backend.backend.controller.post.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class PostDeleteBoard {
    private String delete_id;
    public PostDeleteBoard(String id) {
        this.delete_id=id;
    }
    public PostDeleteBoard()
    {

    }
}
