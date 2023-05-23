package com.backend.backend.controller.trading;

import com.backend.backend.DataProcessing.TokenParsing;
import com.backend.backend.controller.trading.tradeDto.TradePost;
import com.backend.backend.service.TalentTradingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/vi/Trading_Talent")
@RequiredArgsConstructor
public class TradingController {

    private final TalentTradingService talentTradingService;


    /**
     * Post에 가격에 맞게 거래함
     * @param tradePost
     * @return
     */
    @PostMapping("/trade")
    public void trade(HttpServletRequest request, @RequestBody @Valid TradePost tradePost){
        TokenParsing tokenParsing=new TokenParsing();
        String result= tokenParsing.ExtractNickname(request);
        // 클라이언트에서 postID값만 보내서 받는다.
        talentTradingService.talentTrading(tradePost.getTradePost_id(),result);
    }

}
