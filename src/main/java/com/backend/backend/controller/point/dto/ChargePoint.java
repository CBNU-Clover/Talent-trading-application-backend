package com.backend.backend.controller.point.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ChargePoint {

    private Long point;


    public ChargePoint(Long point) {
        this.point = point;

    }

    public ChargePoint() {
    }
}
