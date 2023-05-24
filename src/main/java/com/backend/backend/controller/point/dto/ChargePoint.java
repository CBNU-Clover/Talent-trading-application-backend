package com.backend.backend.controller.point.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ChargePoint {

    private Long point;
    private String sender;

    public ChargePoint(Long point,String sender) {
        this.point = point;
        this.sender=sender;
    }

    public ChargePoint() {
    }
}
