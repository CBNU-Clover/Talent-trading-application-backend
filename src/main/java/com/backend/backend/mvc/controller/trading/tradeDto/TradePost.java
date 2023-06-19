package com.backend.backend.mvc.controller.trading.tradeDto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TradePost {
    private Long tradePost_id;

    public TradePost(Long tradePost_id) {
        this.tradePost_id = tradePost_id;
    }

    public TradePost() {
    }
}
