package com.backend.backend.mvc.controller.trading.tradeDto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TradingHistory {
    private String trade_postname;
    private Long trade_price;
    private String buyer_nickname;
    private String seller_nickname;

    private String date;
    private String trading_image_url;

    public TradingHistory( String trade_postname,Long trade_price, String buyer_nickname,String seller_nickname,String date,String trading_image_url) {

        this.trade_postname = trade_postname;
        this.trade_price=trade_price;
        this.buyer_nickname=buyer_nickname;
        this.seller_nickname=seller_nickname;
        this.date=date;
        this.trading_image_url=trading_image_url;
    }

    public TradingHistory()
    {
    }
}
