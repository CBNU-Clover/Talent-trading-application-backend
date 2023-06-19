package com.backend.backend.mvc.controller.point.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ShowPointDTO {
    private Long point;
    private String nickname;

    public ShowPointDTO(Long point,String nickname) {
        this.point = point;
        this.nickname=nickname;

    }

    public ShowPointDTO() {
    }

}
